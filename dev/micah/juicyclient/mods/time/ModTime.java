package dev.micah.juicyclient.mods.time;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dev.micah.juicyclient.mods.ModDraggable;
import dev.micah.juicyclient.render.ScreenPosition;

public class ModTime extends ModDraggable {

	public String getTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");  
		LocalDateTime now = LocalDateTime.now();  
		if (Integer.valueOf(dtf.format(now).split(":")[0]) > 12) {
			String newTime = (Integer.valueOf(dtf.format(now).split(":")[0]) - 12) + ":" + dtf.format(now).split(":")[1] + "PM";
			return newTime;
		}
		return dtf.format(now) + "AM";
	}
	
	@Override
	public int getWidth() {
		return font.getStringWidth(getTime());
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		font.drawString(getTime(), pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString(getTime(), pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
	}

}
