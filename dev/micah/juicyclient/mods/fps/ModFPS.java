package dev.micah.juicyclient.mods.fps;

import java.awt.Color;

import dev.micah.juicyclient.mods.ModDraggable;
import dev.micah.juicyclient.render.ScreenPosition;
import net.minecraft.client.Minecraft;

public class ModFPS extends ModDraggable {

	public static int fps;
	
	@Override
	public int getWidth() {
		return font.getStringWidth("FPS: 140");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		font.drawString("FPS: " + fps, pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString("FPS: 140", pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
	}

}
