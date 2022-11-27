package dev.micah.juicyclient.mods;

import java.awt.Color;
import java.io.File;

import dev.micah.juicyclient.render.ScreenPosition;
import dev.micah.juicyclient.render.screen.IRenderer;


public abstract class ModDraggable extends Mod implements IRenderer {

	protected ScreenPosition pos;
	private static ModDraggable instance;
	
	public final int getLineOffset(ScreenPosition pos, int lineNum) {
		return pos.getAbsoluteY() + getLineOffset(lineNum);
	}

	private int getLineOffset(int lineNum) {
		return (font.FONT_HEIGHT + 3) * lineNum;
	}

	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
	}
	
	@Override
	public ScreenPosition load() {
		return pos;
	}
	
}
