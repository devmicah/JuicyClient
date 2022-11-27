package dev.micah.juicyclient.cosmetics;

import org.apache.logging.log4j.core.helpers.Loader;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class WingRenderer extends ModelBase{
	
	private Minecraft mc;
	private static ResourceLocation location;
	private ModelRenderer wing;
	private ModelRenderer wingTip;
	private boolean playerUsesFullHeight;
	
	public static ResourceLocation getLocation() {
		return location;
	}
	
	public static void setLocation(ResourceLocation location) {
		WingRenderer.location = location;
	}

	public WingRenderer()
	{
		this.mc = Minecraft.getMinecraft();
        this.setTextureOffset("wing.bone", 0, 0);
        this.setTextureOffset("wing.skin", -10, 8);
        this.setTextureOffset("wingtip.bone", 0, 5);
        this.setTextureOffset("wingtip.skin", -10, 18);
        (this.wing = new ModelRenderer((ModelBase)this, "wing")).setTextureSize(30, 30);
        this.wing.setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.wing.addBox("bone", -10.0f, -1.0f, -1.0f, 10, 2, 2);
        this.wing.addBox("skin", -10.0f, 0.0f, 0.5f, 10, 0, 10);
        (this.wingTip = new ModelRenderer((ModelBase)this, "wingtip")).setTextureSize(30, 30);
        this.wingTip.setRotationPoint(-10.0f, 0.0f, 0.0f);
        this.wingTip.addBox("bone", -10.0f, -0.5f, -0.5f, 10, 1, 1);
        this.wingTip.addBox("skin", -10.0f, 0.0f, 0.5f, 10, 0, 10);
        this.wing.addChild(this.wingTip);
	}

	void renderWings(EntityPlayer player, float partialTicks)
	{
		if (location != null) {
		double scale = 1F;
		 double var9 = player.prevChasingPosX + (player.chasingPosX - player.prevChasingPosX) - (player.prevPosX + (player.posX - player.prevPosX) * (double)1.5);
        double var11 = player.prevChasingPosY + (player.chasingPosY - player.prevChasingPosY) - (player.prevPosY + (player.posY - player.prevPosY) * (double)1);
        double var13 = player.prevChasingPosZ + (player.chasingPosZ - player.prevChasingPosZ) - (player.prevPosZ + (player.posZ - player.prevPosZ) * (double)1);
        float var15 = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * 0.12F;
        double var16 = (double)MathHelper.sin(var15 * (float)Math.PI / 180.0F);
        double var18 = (double)(-MathHelper.cos(var15 * (float)Math.PI / 100.0F));
        float var20 = (float)var11 * 5.0F;
        var20 = MathHelper.clamp_float(var20, -6.0F, 32.0F);
        float var21 = (float)(var9 * var16 + var13 * var18) * 10.0F;
        float var22 = (float)(var9 * var18 - var13 * var16) * 0.0F;
		GL11.glPushMatrix();
		GL11.glScaled(-scale, -scale, scale);
		GlStateManager.rotate(6.0F + var21 / 5.0F + var20, 1.0F, 0.0F, 0.0F);
       GlStateManager.rotate(var22 / 2.0F, 0.0F, 0.0F, 1.0F);
       GlStateManager.rotate(-var22 / 2.0F, 0.0F, 1.0F, 0.0F);
       GlStateManager.rotate(0.0F, 0.0F, 0.4F, 0.0F);
       GlStateManager.translate(0, 0.05, 0.15);
        if (player.isSneaking()) {
        	GL11.glTranslated(0D, -0.225D / scale, 0D);
        }
        GL11.glTranslated(0D, -0.1D / scale, 0D);
        this.mc.getRenderManager().playerRenderer.bindTexture(getLocation());
        for (int j = 0; j < 2; ++j) {
            GL11.glEnable(2884);
            final float f11 = System.currentTimeMillis() % 1000L / 1000.0f * 3.1415927f * 2.0f;
            this.wing.rotateAngleX = (float) Math.toRadians(55F) - (float) Math.cos((double)f11) * 0.2F;
			this.wing.rotateAngleY = (float) Math.toRadians(20F) + (float) Math.sin(f11) * 0.4F;
			this.wing.rotateAngleZ = (float) Math.toRadians(20F);
            this.wingTip.rotateAngleZ = -(float)(Math.sin(f11 + 2.0f) + 0.5) * 0.75f;
            this.wing.render(0.0625f);
            GL11.glScalef(-1.0f, 1.0f, 1.0f);
            if (j == 0) {
                GL11.glCullFace(1028);
            }
        }
        GL11.glCullFace(1029);
        GL11.glDisable(2884);
        GL11.glColor3f(255.0f, 255.0f, 255.0f);
        GL11.glPopMatrix();
		}
	}

	private float interpolate(float yaw1, float yaw2, float percent)
	{
		float f = (yaw1 + (yaw2 - yaw1) * percent) % 360;

		if (f < 0)
		{
			f += 260;
		}

		return f;
	}

}
