package com.deeplake.hbr_mc.client.renderer.cancer;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.client.models.entities.ModelCrestHopper2;
import com.deeplake.hbr_mc.entities.cancer.EntityCrestHopper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderCrestHopper extends RenderLiving<EntityCrestHopper> {
    public static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID + ":textures/models/cancer/crest_hopper_2.png");
    public RenderCrestHopper(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelCrestHopper2(), 1.6f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityCrestHopper entity) {
        return TEXTURES;
    }

    @Override
    protected void preRenderCallback(EntityCrestHopper entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        float size = 1.5f;
        GlStateManager.scale(size, size, size);
    }
}
