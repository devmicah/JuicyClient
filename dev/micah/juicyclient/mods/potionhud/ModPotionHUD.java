package dev.micah.juicyclient.mods.potionhud;

import java.awt.Color;
import java.util.Collection;

import dev.micah.juicyclient.mods.ModDraggable;
import dev.micah.juicyclient.render.ScreenPosition;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ModPotionHUD extends ModDraggable {
	
	@Override
	public int getWidth() {
		return font.getStringWidth("Speed");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT * 2;
	}

	@Override
	public void render(ScreenPosition pos) {
		int i = 80;
        int i2 = 16;
        Collection<PotionEffect> collection = this.mc.thePlayer.getActivePotionEffects();

        if (!collection.isEmpty())
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            int l = 33;

            if (collection.size() > 5)
            {
                l = 132 / (collection.size() - 1);
            }

            for (PotionEffect potioneffect : this.mc.thePlayer.getActivePotionEffects())
            {
                Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                String s1 = I18n.format(potion.getName(), new Object[0]);
                if (potioneffect.getAmplifier() == 1)
                {
                    s1 = s1 + " " + I18n.format("enchantment.level.2", new Object[0]);
                }
                else if (potioneffect.getAmplifier() == 2)
                {
                    s1 = s1 + " " + I18n.format("enchantment.level.3", new Object[0]);
                }
                else if (potioneffect.getAmplifier() == 3)
                {
                    s1 = s1 + " " + I18n.format("enchantment.level.4", new Object[0]);
                }
                font.drawString(s1, pos.getAbsoluteX(), pos.getAbsoluteY() + i2 - 30, Color.WHITE.getRGB(), true);
                String s = Potion.getDurationString(potioneffect);
                font.drawString(s, pos.getAbsoluteX(), pos.getAbsoluteY() + i2 + 10 - 30, 8355711, true);
                i2 += l;
            }
        }
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString("Speed", pos.getAbsoluteX(), pos.getAbsoluteY(), Color.WHITE.getRGB());
		font.drawString("3:00", pos.getAbsoluteX(), pos.getAbsoluteY() + 10, Color.WHITE.getRGB());
	}

}
