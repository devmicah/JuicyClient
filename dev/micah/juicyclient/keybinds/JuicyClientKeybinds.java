package dev.micah.juicyclient.keybinds;

import dev.micah.juicyclient.event.EventTarget;
import dev.micah.juicyclient.event.impl.EventKey;
import dev.micah.juicyclient.event.impl.EventTick;
import dev.micah.juicyclient.render.screen.HUDConfigScreen;
import dev.micah.juicyclient.render.screen.HUDManager;
import net.minecraft.client.Minecraft;

public class JuicyClientKeybinds {
	
	@EventTarget
	public void onTick(EventKey event) {
		if (Minecraft.getMinecraft().gameSettings.clientModHUD.isKeyDown()) {
			Minecraft.getMinecraft().displayGuiScreen(new HUDConfigScreen(HUDManager.getInstance()));
		}
	}

}
