package com.deeplake.hbr_mc.client.renderer.cancer;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.client.models.entities.ModelMarionette;
import com.deeplake.hbr_mc.entities.cancer.EntityMarionette;
import com.deeplake.hbr_mc.entities.cancer.EntitySmallHopper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderMarionette extends RenderLiving<EntityMarionette> {
    public static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID + ":textures/models/cancer/marionette.png");
    public RenderMarionette(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelMarionette(), 0.5f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMarionette entity) {
        return TEXTURES;
    }

    @Override
    protected void preRenderCallback(EntityMarionette entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        float size = 1.4f;
        GlStateManager.scale(size, size, size);
    }
}
