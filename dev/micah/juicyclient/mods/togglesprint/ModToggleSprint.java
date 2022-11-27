package dev.micah.juicyclient.mods.togglesprint;

import java.awt.Color;

import dev.micah.juicyclient.event.EventTarget;
import dev.micah.juicyclient.event.impl.EventKey;
import dev.micah.juicyclient.mods.ModDraggable;
import dev.micah.juicyclient.render.ScreenPosition;

public class ModToggleSprint extends ModDraggable {
	
	private String textToRender = "";
	
	//settings
	public boolean flyBoost = true;
	public float flyBoostFactor = 4;
	public int keyHoldTicks = 7;
	public boolean enabled = false;
	
	@Override
	public int getWidth() {
		return font.getStringWidth(textToRender);
	}
	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}
	@Override
	public void render(ScreenPosition pos) {
		String text = "";
		if (mc.gameSettings.keyBindForward.isKeyDown()) {
			mc.thePlayer.setSprinting(true);
		}
		text = "[Sprinting (Toggled)]";
		if (mc.thePlayer.capabilities.isFlying) {
			mc.thePlayer.capabilities.setFlySpeed(1);
			text = "[Flying (Boost x2)]";
		}
		font.drawString(text, pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		textToRender = "[Toggle Notifactions]";
		font.drawString(textToRender, pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
	}

}
