package dev.micah.juicyclient.mods.fps;

import dev.micah.juicyclient.event.EventTarget;
import dev.micah.juicyclient.event.impl.EventRender;
import dev.micah.juicyclient.event.impl.EventTick;
import net.minecraft.client.Minecraft;

public class EventFPS {
	
	@EventTarget
	public void onRender(EventRender eventTick) {
		ModFPS.fps = Minecraft.getMinecraft().getDebugFPS();
	}

}
