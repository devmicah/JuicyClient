package dev.micah.juicyclient.mods.coords;

import java.awt.Color;

import dev.micah.juicyclient.mods.ModDraggable;
import dev.micah.juicyclient.render.ScreenPosition;
import net.minecraft.client.Minecraft;

public class ModCoordinates extends ModDraggable {
	
	public boolean verticle = true;
	
	public String x() {
		return "X: " + Minecraft.getMinecraft().thePlayer.getPosition().getX();
	}
	
	public String y() {
		return "Y: " + Minecraft.getMinecraft().thePlayer.getPosition().getY();
	}
	
	public String z() {
		return "Z: " + Minecraft.getMinecraft().thePlayer.getPosition().getZ();
	}

	@Override
	public int getWidth() {
		if (verticle) {
			return font.getStringWidth("X: 0");
		}
		return font.getStringWidth(x() + " " + y() + " " + z());
	}

	@Override
	public int getHeight() {
		if (verticle) {
			return 26;
		}
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (verticle) {
			font.drawString(x(), pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
			font.drawString(y(), pos.getAbsoluteX(), pos.getAbsoluteY() + 10, Color.WHITE.getRGB());
			font.drawString(z(), pos.getAbsoluteX(), pos.getAbsoluteY() + 20, Color.WHITE.getRGB());
		} else {
			font.drawString(x() + " " + y() + " " + z(), pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (verticle) {
			font.drawString("X: 0", pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
			font.drawString("Y: 0", pos.getAbsoluteX(), pos.getAbsoluteY() + 10, Color.WHITE.getRGB());
			font.drawString("Z: 0", pos.getAbsoluteX(), pos.getAbsoluteY() + 20, Color.WHITE.getRGB());
		} else {
			font.drawString(x() + " " + y() + " " + z(), pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
		}
	}
	
}
