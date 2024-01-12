package com.deeplake.hbr_mc.client.renderer.cancer;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.client.models.entities.ModelMarionette;
import com.deeplake.hbr_mc.client.models.entities.ModelSmallHopper;
import com.deeplake.hbr_mc.entities.cancer.EntitySmallHopper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderSmallHopper extends RenderCancer<EntitySmallHopper> {
    public static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID + ":textures/models/cancer/small_hopper.png");
    public RenderSmallHopper(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelSmallHopper(), 1f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySmallHopper entity) {
        return TEXTURES;
    }

    @Override
    protected void preRenderCallback(EntitySmallHopper entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        size = 1.8f;
        GlStateManager.scale(size, size, size);
    }
}
