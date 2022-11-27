package dev.micah.juicyclient.mods.cps;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import dev.micah.juicyclient.mods.ModDraggable;
import dev.micah.juicyclient.render.ScreenPosition;

public class ModCPS extends ModDraggable {

	@Override
	public int getWidth() {
		return font.getStringWidth(getCPSLeft() + " : " + getCPSRight());
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		boolean pressed = Mouse.isButtonDown(0);
		if (pressed != this.wasPressed) {
			this.lastPressed = System.currentTimeMillis();
			this.wasPressed = pressed;
			if (pressed) {
				this.clicks.add(this.lastPressed);
			}
		}
		
		boolean pressed1 = Mouse.isButtonDown(1);
		if (pressed1 != this.wasPressed1) {
			this.lastPressed1 = System.currentTimeMillis();
			this.wasPressed1 = pressed1;
			if (pressed1) {
				this.clicks1.add(this.lastPressed1);
			}
		}
		font.drawString(getCPSLeft() + " : " + getCPSRight(), pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString(getCPSLeft() + " : " + getCPSRight(), pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
	}
	
	private static List<Long> clicks = new ArrayList<Long>();
	private static boolean wasPressed;
	private static long lastPressed;
	
	public static int getCPSLeft() {
		final long time = System.currentTimeMillis();
		clicks.removeIf(aLong -> aLong + 1000 < time);
		return clicks.size();
	}
	
	private static List<Long> clicks1 = new ArrayList<Long>();
	private static boolean wasPressed1;
	private static long lastPressed1;
	
	public static int getCPSRight() {
		final long time = System.currentTimeMillis();
		clicks1.removeIf(aLong -> aLong + 1000 < time);
		return clicks1.size();
	}
	
}
