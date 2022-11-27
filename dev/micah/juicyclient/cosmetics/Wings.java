package dev.micah.juicyclient.cosmetics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public class Wings implements LayerRenderer<AbstractClientPlayer>{

	private final RenderPlayer playerRenderer;
	private Minecraft mc;
	private boolean playerUsesFullHeight;
	private ModelRenderer wing;
	private ModelRenderer wingTip;
    public Wings(RenderPlayer playerRendererIn)
    {
        this.playerRenderer = playerRendererIn;
        this.mc = Minecraft.getMinecraft();
    }
	
	public void doRenderLayer(AbstractClientPlayer clientPlayer, float p_177166_2_, float p_177166_3_, float p_177166_4_, float p_177166_5_, float p_177166_6_, float p_177166_7_, float p_177166_8_) {
		if (clientPlayer.hasPlayerInfo() && !clientPlayer.isInvisible() && clientPlayer.getNameClear().equals("SkyThund3r")) {
			WingRenderer render = new WingRenderer();
			render.renderWings(clientPlayer, 1.0F);
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
	
	
	

}
