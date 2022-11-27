package dev.micah.juicyclient.mods;

import dev.micah.juicyclient.event.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

/**
 * 
 * 

Reach display
Auto GG (hypixel)
Auto tip (hypixel)
Anti-spam (any of the same duplicated messages gets replaced with “message content [x<AMOUNT of times sent>]” like Lunar)
Chat mention (if someone says your name, a sound gets played OR your name is highlighted // togglable and text colour is optional)
1.7 Animations
Hypixel Level (display the guys hypixel level above head)

 *
 */

public class Mod {
	
	protected final Minecraft mc;
	protected final FontRenderer font;
	
	public Mod() {
		this.mc = Minecraft.getMinecraft();
		this.font = mc.fontRendererObj;
		
		setEnabled(isEnabled);
	}
	
	private boolean isEnabled = true;
	
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = !isEnabled;
		
		if (isEnabled) {
			EventManager.register(this);
		} else {
			EventManager.unregister(this);
		}
	}
	
	public boolean isEnabled() {
		return this.isEnabled;
	}
	
}
