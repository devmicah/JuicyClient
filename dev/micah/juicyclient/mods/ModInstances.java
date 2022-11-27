package dev.micah.juicyclient.mods;

import dev.micah.juicyclient.mods.armorhud.ModArmorHUD;
import dev.micah.juicyclient.mods.coords.ModCoordinates;
import dev.micah.juicyclient.mods.cps.ModCPS;
import dev.micah.juicyclient.mods.fps.ModFPS;
import dev.micah.juicyclient.mods.keystrokes.ModKeystrokes;
import dev.micah.juicyclient.mods.perspective.ModPerspective;
import dev.micah.juicyclient.mods.ping.ModPing;
import dev.micah.juicyclient.mods.potionhud.ModPotionHUD;
import dev.micah.juicyclient.mods.time.ModTime;
import dev.micah.juicyclient.mods.togglesprint.ModToggleSprint;
import dev.micah.juicyclient.render.screen.HUDManager;

public class ModInstances {
	
	private static ModFPS modFps;
	private static ModKeystrokes modKeystrokes;
	private static ModCPS modCps;
	private static ModArmorHUD modArmorHud;
	private static ModCoordinates modCoordinates;
	private static ModPerspective modPerspective;
	private static ModToggleSprint modToggleSprint;
	private static ModPotionHUD modPotionHud;
	private static ModPing modPing;
	private static ModTime modTime;
	
	public static void register(HUDManager api) {
		modFps = new ModFPS();
		modCoordinates = new ModCoordinates();
		modKeystrokes = new ModKeystrokes();
		modCps = new ModCPS();
		modArmorHud = new ModArmorHUD();
		modPerspective = new ModPerspective();
		modToggleSprint = new ModToggleSprint();
		modPotionHud = new ModPotionHUD();
		modPing = new ModPing();
		modTime = new ModTime();
		api.register(modFps);
		api.register(modKeystrokes);
		api.register(modCps);
		api.register(modArmorHud);
		api.register(modCoordinates);
		api.register(modPerspective);
		api.register(modToggleSprint);
		api.register(modPotionHud);
		api.register(modPing);
		api.register(modTime);
	}
	
	public static ModFPS getModFps() {
		return modFps;
	}
	
	public static ModKeystrokes getModKeystrokes() {
		return modKeystrokes;
	}
	
	public static ModCPS getModCps() {
		return modCps;
	}
	
	public static ModArmorHUD getModArmorHud() {
		return modArmorHud;
	}
	
	public static ModCoordinates getModCoordinates() {
		return modCoordinates;
	}
	
	public static ModPerspective getModPerspective() {
		return modPerspective;
	}

	public static ModToggleSprint getModToggleSprint() {
		return modToggleSprint;
	}
	
	public static ModTime getModTime() {
		return modTime;
	}
	
	public static ModPotionHUD getModPotionHud() {
		return modPotionHud;
	}
	
	public static ModPing getModPing() {
		return modPing;
	}
	
}
