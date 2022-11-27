package dev.micah.juicyclient.render.screen;

import dev.micah.juicyclient.render.ScreenPosition;

public interface IRenderConfig {

	public void save(ScreenPosition pos);
	
	public ScreenPosition load();
	
}
