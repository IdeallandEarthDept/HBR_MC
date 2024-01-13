package com.deeplake.hbr_mc.client.renderer.cancer;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.client.models.entities.ModelMarionette;
import com.deeplake.hbr_mc.entities.cancer.EntityDoll;
import com.deeplake.hbr_mc.entities.cancer.EntityMarionette;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderDoll extends RenderLiving<EntityDoll> {
    public static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID + ":textures/models/cancer/marionette.png");
    public RenderDoll(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelMarionette(), 0.7f);
    }

    @Override
    protected void preRenderCallback(EntityDoll entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        float size = 1.7f;
        GlStateManager.scale(size, size, size);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDoll entity) {
        return TEXTURES;
    }
}
