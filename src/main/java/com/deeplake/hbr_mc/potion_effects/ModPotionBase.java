package com.deeplake.hbr_mc.potion_effects;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.RegisterEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

public class ModPotionBase extends Potion {
    protected static final ResourceLocation resource = new ResourceLocation(Main.MODID,"textures/misc/potions.png");
    protected final int iconIndex;
    public static final String POTION_PREFIX = Main.MODID+".potion.";
    public UUID UUID_CLIENT = null;

    public ModPotionBase(boolean isBadEffectIn, int liquidColorIn, String name, int icon) {
        super(isBadEffectIn, liquidColorIn);
        setRegistryName(new ResourceLocation(Main.MODID, name));

        setPotionName(POTION_PREFIX + name);
        iconIndex = icon;

        RegisterEffects.INSTANCES.add(this);
    }

    @SideOnly(Side.CLIENT)
    protected void render(int x, int y, float alpha) {
        Minecraft.getMinecraft().renderEngine.bindTexture(resource);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buf = tessellator.getBuffer();
        buf.begin(7, DefaultVertexFormats.POSITION_TEX);
        GlStateManager.color(1, 1, 1, alpha);

        int textureX = iconIndex % 14 * 18;
        int textureY = 198 - iconIndex / 14 * 18;

        buf.pos(x, y + 18, 0).tex(textureX * 0.00390625, (textureY + 18) * 0.00390625).endVertex();
        buf.pos(x + 18, y + 18, 0).tex((textureX + 18) * 0.00390625, (textureY + 18) * 0.00390625).endVertex();
        buf.pos(x + 18, y, 0).tex((textureX + 18) * 0.00390625, textureY * 0.00390625).endVertex();
        buf.pos(x, y, 0).tex(textureX * 0.00390625, textureY * 0.00390625).endVertex();

        tessellator.draw();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        render(x + 6, y + 7, 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        render(x + 3, y + 3, alpha);
    }

    /**
     * Returns true if the potion has a associated status icon to display in then inventory when active.
     */
    @SideOnly(Side.CLIENT)
    public boolean hasStatusIcon()
    {
        return this.iconIndex >= 0;
    }

    public ModPotionBase setUUID_CLIENT(String s)
    {
        UUID_CLIENT = UUID.fromString(s);
        registerPotionAttributeModifier(SharedMonsterAttributes.MAX_HEALTH, s, 0f, 0);
        return this;
    }

    public boolean hasPotion(EntityLivingBase livingBase)
    {
        if (livingBase.getEntityWorld() != null)
        {
            if (livingBase.getEntityWorld().isRemote)
            {
                IAttributeInstance attribute = livingBase.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH) ;
                if (attribute==null || attribute.getModifier(UUID_CLIENT) == null)
                {
                    return false;
                }
                else {
                    return true;
                }
            } else {
                return livingBase.isPotionActive(this);
            }
        }
        else {
            return false;
        }
    }
}
