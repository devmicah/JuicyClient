package dev.micah.juicyclient.mods.keystrokes;

import org.lwjgl.opengl.GL11;

import dev.micah.juicyclient.mods.ModDraggable;
import dev.micah.juicyclient.mods.ModInstances;
import dev.micah.juicyclient.render.ScreenPosition;

import java.awt.Color;
import java.awt.Font;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;

public class ModKeystrokes extends ModDraggable {
	
	public static enum KeystrokesMode {
		
		WASD(Key.W, Key.A, Key.S, Key.D),
		WASD_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, Key.SPACE),
		WASD_SPRINT(Key.W, Key.A, Key.S, Key.D, new Key("Sprint", Minecraft.getMinecraft().gameSettings.keyBindSprint, 1, 41, 58, 18, "SPRINT")),
		WASD_SPRINT_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, new Key("Sprint", Minecraft.getMinecraft().gameSettings.keyBindSprint, 1, 61, 58, 18, "SPINTMOUSE"))
		;
		private final Key[] keys;
		private int width = 0;
		private int height = 0;
		
		public int getHeight() {
			return height;
		}
		
		public int getWidth() {
			return width;
		}
		
		public Key[] getKeys() {
			return keys;
		}
		
		private KeystrokesMode(Key... keysIn) {
			this.keys = keysIn;
			
			for (Key key : keys) {
				this.width = Math.max(this.width, key.getX() + key.getWidth());
				this.height = Math.max(this.height, key.getY() + key.getHeight());
			}
		}
		
	}
	
	public static int dif;
	
	public static boolean showCps = true;
	
	public static class Key {
		private static final Key W = new Key("W", Minecraft.getMinecraft().gameSettings.keyBindForward, 21- dif, 1- dif, 18 - dif, 18 - dif, "W");
		private static final Key A = new Key("A", Minecraft.getMinecraft().gameSettings.keyBindLeft, 1- dif, 21- dif, 18- dif, 18- dif, "A");
		private static final Key S = new Key("S", Minecraft.getMinecraft().gameSettings.keyBindBack, 21- dif, 21- dif, 18- dif, 18- dif, "S");
		private static final Key D = new Key("D", Minecraft.getMinecraft().gameSettings.keyBindRight, 41- dif, 21- dif, 18- dif, 18- dif, "D");
		
		private static final Key LMB = new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 1- dif, 41- dif, 28- dif, 18- dif, "LMB");
		private static final Key RMB = new Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 31- dif, 41- dif, 28- dif, 18- dif, "RMB");
		private static final Key SPACE = new Key("JUMP", Minecraft.getMinecraft().gameSettings.keyBindJump, 1 - dif, 62-dif, 58 - dif, 18 -dif, "SB");
		
		private String name;
		private final KeyBinding keyBind;
		private final int x;
		private final int y;
		private int width;
		private int height;
		private String id;
		
		public Key(String name, KeyBinding keyBind, int x, int y, int width, int height, String id) {
			this.name = name;
			this.keyBind = keyBind;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.id = id;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public boolean isDown() {
			return keyBind.isKeyDown();
		}
		
		public int getHeight() {
			return height;
		}
		
		public int getWidth() {
			return width;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public String getName() {
			return name;
		}
		
		public String getId() {
			return id;
		}
		
	}
	
	private ScreenPosition pos;
	private KeystrokesMode mode = KeystrokesMode.WASD_MOUSE;
	
	public void setMode(KeystrokesMode mode) {
		this.mode = mode;
	}
	
	@Override
	public int getWidth() {
		return mode.getWidth();
	}

	@Override
	public int getHeight() {
		return mode.getHeight();
	}


	public static int modifer;
	
	@Override
	public void render(ScreenPosition pos) {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		for (Key key : mode.getKeys()) {
			int textWidth = font.getStringWidth(key.getName());
			Gui.drawRect(
					pos.getAbsoluteX() + key.getX(),
					pos.getAbsoluteY() + key.getY(),
					pos.getAbsoluteX() + key.getX() + key.getWidth(),
					pos.getAbsoluteY() + key.getY() + key.getHeight(),
					getWhatColorShouldBe(key));
			if (showCps && key.getId().contains("MB")) {
				if (key.getId().equals("RMB")) {
					key.setName(String.valueOf(ModInstances.getModCps().getCPSRight()));
				}
				if (key.getId().equals("LMB")) {
					key.setName(String.valueOf(ModInstances.getModCps().getCPSLeft()));
				}
			}
			
			font.drawString(key.getName(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2 - modifer, 
					pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4 - modifer, 
					key.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());

		}
		
		GL11.glDisable(GL11.GL_BLEND);
		
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		for (Key key : mode.getKeys()) {
			
			int textWidth = font.getStringWidth(key.getName());
			Gui.drawRect(
					pos.getAbsoluteX() + key.getX(),
					pos.getAbsoluteY() + key.getY(),
					pos.getAbsoluteX() + key.getX() + key.getWidth(),
					pos.getAbsoluteY() + key.getY() + key.getHeight(),
					getWhatColorShouldBe(key));
			
			font.drawString(key.getName(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2 - modifer, 
					pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4 - modifer, 
					key.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());

		}
		
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}
	
	public int getWhatColorShouldBe(Key key) {
		if (key.isDown()) {
			return new Color(255, 255, 255, 102).getRGB();
		}
		return new Color(0, 0, 0, 102).getRGB();
	}
	
}
