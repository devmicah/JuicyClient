package dev.micah.juicyclient.render.screen;

import dev.micah.juicyclient.render.ScreenPosition;
import dev.micah.juicyclient.render.screen.*;

public interface IRenderer extends IRenderConfig {

	int getWidth();
	int getHeight();
	
	void render(ScreenPosition pos);
	
	default void renderDummy(ScreenPosition pos) {
		render(pos);
	}
	
	default boolean isEnabled() {
		return true;
	}

}
