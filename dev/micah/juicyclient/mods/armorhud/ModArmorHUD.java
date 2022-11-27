package dev.micah.juicyclient.mods.armorhud;

import org.lwjgl.opengl.GL11;

import dev.micah.juicyclient.mods.ModDraggable;
import dev.micah.juicyclient.render.ScreenPosition;
import dev.micah.juicyclient.render.screen.HUDManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModArmorHUD extends ModDraggable {
	

	@Override
	public int getWidth() {
		return 64;
	}

	@Override
	public int getHeight() {
		return 64;
	}

	@Override
	public void render(ScreenPosition pos) {
		for (int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
			ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
			renderItemStack(pos, i, itemStack);
		}
	}
	
	public void renderDummy(ScreenPosition pos) {
		if (!HUDManager.getInstance().getUnregisteredRenderers().contains(this)) {
			renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
			renderItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
			renderItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
			renderItemStack(pos, 0, new ItemStack(Items.diamond_boots));
		}
	}
	
	
 
	private void renderItemStack(ScreenPosition pos, int i, ItemStack itemStack) {
		if(itemStack == null) {
			return;
		}
		int yAdd = (-16 * i) + 48;
		
		GL11.glPushMatrix();
		
		if (itemStack.getItem().isDamageable()) {
			double damage = ((itemStack.getMaxDamage() - itemStack.getItemDamage()) / (double) itemStack.getMaxDamage() * 100);
			font.drawString(String.format("%.2f%%", damage), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, -1);
		}
		
		RenderHelper.enableGUIStandardItemLighting();
		mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, pos.getAbsoluteX(), pos.getAbsoluteY() + yAdd);
		GL11.glPopMatrix();
	}

}
