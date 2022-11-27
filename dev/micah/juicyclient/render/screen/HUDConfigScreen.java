package dev.micah.juicyclient.render.screen;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

import org.lwjgl.input.Keyboard;

import dev.micah.juicyclient.gui.GuiHelper;
import dev.micah.juicyclient.mods.ModInstances;
import dev.micah.juicyclient.mods.ModManager;
import dev.micah.juicyclient.mods.coords.ModCoordinates;
import dev.micah.juicyclient.render.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class HUDConfigScreen extends GuiScreen {

	private final HashMap<IRenderer, ScreenPosition> renderers = new HashMap<IRenderer, ScreenPosition>();
	
	private Optional<IRenderer> selectedRenderer = Optional.empty();
	
	private int prevX, prevY;
	
	public HUDConfigScreen(HUDManager api) {

		Collection<IRenderer> registeredRenderers = api.getRegisteredRenderers();
		
		for(IRenderer ren : registeredRenderers) {
			
			ScreenPosition pos = ren.load();
			
			if(pos == null) {
				pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
			}
			
			adjustBounds(ren, pos);
			this.renderers.put(ren, pos);
		}

	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		final float zBackup = this.zLevel;
		this.zLevel = 200;
		
		this.drawHollowRect(0, 0, this.width - 1, this.height - 1, Color.BLACK.getRGB());
		
		for(IRenderer renderer : renderers.keySet()) {
			if (!ModManager.isEnabled(renderer)) {
				continue;
			}
			ScreenPosition pos = renderers.get(renderer);
			
			renderer.renderDummy(pos);
			
			if (renderer.getClass().getName().contains("Keystrokes") || renderer.getClass().getName().contains("Armor") || (renderer.getClass().getName().contains("Coord") && ModInstances.getModCoordinates().verticle)) {
				GuiHelper.getInstance().drawPicture(pos.getAbsoluteX() - 5, pos.getAbsoluteY() - 5, renderer.getWidth() + 12, renderer.getHeight() + 12, "juicyclient/gui/mod-outline-tall.png");
			} else {
				GuiHelper.getInstance().drawPicture(pos.getAbsoluteX() - 2, pos.getAbsoluteY() - 2, renderer.getWidth() + 3, renderer.getHeight() + 2, "juicyclient/gui/mod-outline.png");
			}
			
		}
		
		this.zLevel = zBackup;

	}

	private void drawHollowRect(int x, int y, int w, int h, int color) {
		this.drawHorizontalLine(x, x + w, y, color);
		this.drawHorizontalLine(x, x + w, y + h, color);
		
		this.drawVerticalLine(x, y + h, y, color);
		this.drawVerticalLine(x + w, y + h, y, color);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if(keyCode == Keyboard.KEY_ESCAPE) {
			renderers.entrySet().forEach((entry) -> {
				entry.getKey().save(entry.getValue());
			});
			this.mc.displayGuiScreen(null);
		}
	}
	
	@Override
	protected void mouseClickMove(int x, int y, int button, long time) {
		if(selectedRenderer.isPresent()) {
			moveSelectedRenderBy(x - prevX, y - prevY);
		}
		
		this.prevX = x;
		this.prevY = y;
	}

	private void moveSelectedRenderBy(int offsetX, int offsetY) {
		IRenderer renderer = selectedRenderer.get();
		ScreenPosition pos = renderers.get(renderer);
		
		pos.setAbsolute(pos.getAbsoluteX() + offsetX, pos.getAbsoluteY() + offsetY);
		
		adjustBounds(renderer, pos);
	}
	
	@Override
	public void onGuiClosed() {
		
		for(IRenderer renderer : renderers.keySet()) {
			renderer.save(renderers.get(renderer));
		}
		
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	private void adjustBounds(IRenderer renderer, ScreenPosition pos) {
		
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		
		int screenWidth = res.getScaledWidth();
		int screenHeight = res.getScaledHeight();
		
		int absoluteX = Math.max(0, Math.min(pos.getAbsoluteX(), Math.max(screenWidth - renderer.getWidth(), 0)));
		int absoluteY = Math.max(0, Math.min(pos.getAbsoluteY(), Math.max(screenHeight - renderer.getHeight(), 0)));
		
		pos.setAbsolute(absoluteX, absoluteY);
	}
	
	@Override
	protected void mouseClicked(int x, int y, int mobuttonuseButton) throws IOException {
		this.prevX = x;
		this.prevY = y;
		
		loadMouseOver(x, y);
	}

	private void loadMouseOver(int x, int y) {
		this.selectedRenderer = renderers.keySet().stream().filter(new MouseOverFinder(x, y)).findFirst();
	}
	
	private class MouseOverFinder implements Predicate<IRenderer> {

		private int mouseX, mouseY;
		
		public MouseOverFinder(int x, int y) {
			this.mouseX = x;
			this.mouseY = y;
		}

		@Override
		public boolean test(IRenderer renderer) {

			ScreenPosition pos = renderers.get(renderer);
			
			int absoluteX = pos.getAbsoluteX();
			int absoluteY = pos.getAbsoluteY();
			
			if(mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth()) {
				
				if(mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight()) {
					
					return true;
					
				}
				
			}

			return false;
		}
		
	}
	
}
