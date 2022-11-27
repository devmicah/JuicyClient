package dev.micah.juicyclient.render.screen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import dev.micah.juicyclient.event.EventManager;
import dev.micah.juicyclient.event.EventTarget;
import dev.micah.juicyclient.event.impl.EventRender;
import dev.micah.juicyclient.mods.ModManager;
import dev.micah.juicyclient.render.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;


public class HUDManager {
	
	private static HUDManager instance = null;
	
	public static HUDManager getInstance() {

		if(instance != null) {
			return instance;
		}
		
		instance = new HUDManager();
		EventManager.register(instance);
		
		return instance;

	}
	
	private Set<IRenderer> registeredRenderers = Sets.newHashSet();
	private Set<IRenderer> unregRenderers = Sets.newHashSet();
	private Minecraft mc = Minecraft.getMinecraft();
	
	public void register(IRenderer... renderers) {
		for(IRenderer render : renderers) {
			this.registeredRenderers.add(render);
		}
	}
	
	public void unregister(IRenderer... renderers) {
		for(IRenderer render : renderers) {
			this.registeredRenderers.remove(render);
			unregRenderers.add(render);
		}
	}
	
	public Collection<IRenderer> getRegisteredRenderers() {
		return registeredRenderers;
	}
	
	public void openConfigScreen() {
		mc.displayGuiScreen(new HUDConfigScreen(this));
	}
	
	@EventTarget
	public void onRender(EventRender e) {
		if(mc.currentScreen == null || mc.currentScreen instanceof GuiContainer || mc.currentScreen instanceof GuiChat) {
			for(IRenderer renderer : registeredRenderers) {
				if (ModManager.isEnabled(renderer)) {
					callRenderer(renderer);
				}
			}
		}
	}

	private void callRenderer(IRenderer renderer) {
		if (renderer == null) {
			return;
		}
		if (unregRenderers.contains(renderer)) {
			return;
		}
		
		ScreenPosition pos = renderer.load();
		
		if(pos == null) {
			pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
		}
		
		renderer.render(pos);
	}
	
	public Set<IRenderer> getUnregisteredRenderers() {
		return unregRenderers;
	}
	
}
