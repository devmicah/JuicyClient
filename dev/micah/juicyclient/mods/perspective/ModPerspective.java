package dev.micah.juicyclient.mods.perspective;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import dev.micah.juicyclient.event.EventTarget;
import dev.micah.juicyclient.event.impl.EventKey;
import dev.micah.juicyclient.mods.ModDraggable;
import dev.micah.juicyclient.render.ScreenPosition;
import net.minecraft.client.Minecraft;

public class ModPerspective extends ModDraggable {
	
	private ScreenPosition pos;
	private boolean returnOnRelease = true;
	private boolean perspectiveToggled = false;
	private Minecraft mc = Minecraft.getMinecraft();
	
	private float cameraYaw = 0F;
	private float cameraPitch = 0F;
	
	private int prevPerspective = 0;
	
	@EventTarget
	public void keyboardEvent(EventKey e) {
		if (Keyboard.getEventKey() == mc.gameSettings.togglePerspective.getKeyCode()) {
			if (Keyboard.getEventKeyState()) {
				perspectiveToggled = !perspectiveToggled;
				
				cameraYaw = mc.thePlayer.rotationYaw;
				cameraPitch = mc.thePlayer.rotationPitch;
				
				if (perspectiveToggled) {
					prevPerspective = mc.gameSettings.thirdPersonView;
					mc.gameSettings.thirdPersonView = 1;
				} else {
					mc.gameSettings.thirdPersonView = prevPerspective;
				}
			} else if (returnOnRelease) {
				perspectiveToggled = false;
				mc.gameSettings.thirdPersonView = prevPerspective;
			}
		}
		if (Keyboard.getEventKey() == mc.gameSettings.keyBindTogglePerspective.getKeyCode()) {
			perspectiveToggled = false;
		}
	}
	
	
	public float getCameraYaw() {
		return perspectiveToggled ? cameraYaw : mc.thePlayer.rotationYaw;
	}
	
	public float getCameraPitch() {
		return perspectiveToggled ? cameraPitch : mc.thePlayer.rotationPitch;
	}
	
	@Override
	public int getWidth() {
		return font.getStringWidth("[Perspective]");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (perspectiveToggled) {
			font.drawString("[Perspective (Toggled)]", pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
		}
	}
	
	public void renderDummy(ScreenPosition pos) {
		font.drawString("[Perspective]", pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
	}
	
	public boolean overrideMouse() {
		if(mc.inGameHasFocus && Display.isActive()) {
			
			if(!perspectiveToggled) {
				return true;
			}
			
			mc.mouseHelper.mouseXYChange();
			float f1 = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
			float f2 = f1 * f1 * f1 * 8.0F;
			float f3 = (float) mc.mouseHelper.deltaX * f2;
			float f4 = (float) mc.mouseHelper.deltaY * f2;
			
			cameraYaw += f3 * 0.15F;
			cameraPitch += f4 * 0.15F;
			
			if (cameraPitch > 90) cameraPitch = 90;
			if (cameraPitch < -90) cameraPitch = -90;
					
		}
		return false;
	}
	
}
