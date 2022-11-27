package dev.micah.juicyclient.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiHelper {
	
	private static GuiHelper instance = new GuiHelper();
    private static Minecraft mc = Minecraft.getMinecraft();

    public void drawPicture(int x, int y, int width, int height, String location) {
        ResourceLocation loc = new ResourceLocation(location);
        mc.getTextureManager().bindTexture(loc);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, width, height, width, height);
    }

    public void drawBackgroundPicture(String location) {
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        ResourceLocation loc = new ResourceLocation(location);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(loc);
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight());
    }
    
    public static GuiHelper getInstance() {
    	if (instance == null) {
    		instance = new GuiHelper();
    	}
		return instance;
	}

}
