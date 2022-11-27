package dev.micah.juicyclient.mods.serverip;

import java.awt.Color;

import dev.micah.juicyclient.mods.ModDraggable;
import dev.micah.juicyclient.render.ScreenPosition;

public class ModServerIP extends ModDraggable {

	public static String IP = "Could not detect status... (IP Mod)";
	
	@Override
	public int getWidth() {
		return font.getStringWidth(IP);
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		font.drawString(IP, pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString(IP, pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
	}

}
