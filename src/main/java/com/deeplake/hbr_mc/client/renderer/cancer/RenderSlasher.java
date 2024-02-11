package com.deeplake.hbr_mc.client.renderer.cancer;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.client.models.entities.ModelSlasher;
import com.deeplake.hbr_mc.entities.cancer.EntitySlasher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderSlasher extends RenderLiving<EntitySlasher> {
    public static final ResourceLocation TEXTURES = new ResourceLocation(Main.MODID + ":textures/models/cancer/slasher.png");
    public RenderSlasher(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelSlasher(), 1.6f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySlasher entity) {
        return TEXTURES;
    }

    @Override
    protected void preRenderCallback(EntitySlasher entitylivingbaseIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
        float size = 2f;
        GlStateManager.translate(0, -1.5f, 0);
        GlStateManager.scale(size, size, size);

    }
}
