package dev.micah.juicyclient.mods;

import java.io.File;

import dev.micah.juicyclient.io.FileManager;
import dev.micah.juicyclient.render.screen.IRenderer;

public class ModManager {
	
	public static void initFiles() {
		IRenderer[] mods = new IRenderer[] {ModInstances.getModArmorHud(),
				ModInstances.getModCoordinates(),
				ModInstances.getModCps(),
				ModInstances.getModFps(),
				ModInstances.getModKeystrokes(),
				ModInstances.getModPerspective(),
				ModInstances.getModPing(),
				ModInstances.getModPotionHud(),
				ModInstances.getModTime(),
				ModInstances.getModToggleSprint()};
		for (IRenderer renderer : mods) {
			File mod = new File(FileManager.getHomeDirectory(), renderer.getClass().getSimpleName() + ".mod");
			if (mod.exists()) {
				return;
			}
			FileManager.writeJsonToFile(mod, true);
		}
	}
	
	public static boolean isEnabled(IRenderer mod) {
		File modFile = new File(FileManager.getHomeDirectory(), mod.getClass().getSimpleName() + ".mod");
		return FileManager.readFromJson(modFile, boolean.class);
	}

}
