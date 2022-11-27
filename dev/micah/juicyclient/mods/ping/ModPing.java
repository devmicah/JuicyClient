package dev.micah.juicyclient.mods.ping;

import java.awt.Color;

import dev.micah.juicyclient.mods.ModDraggable;
import dev.micah.juicyclient.render.ScreenPosition;
import net.minecraft.client.Minecraft;

public class ModPing extends ModDraggable {

	@Override
	public int getWidth() {
		return font.getStringWidth("Ping: " + getPing());
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		font.drawString("Ping: " + getPing(), pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString("Ping: " + getPing(), pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
	}

	private int getPing() {
		return Minecraft.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime();
	}
	
}
