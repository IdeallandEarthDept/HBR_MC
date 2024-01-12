package com.deeplake.hbr_mc.client.renderer.cancer;

import com.deeplake.hbr_mc.client.renderer.layer.LayerShell;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelArmorStandArmor;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;

public class RenderCancer<T extends EntityLiving> extends RenderLiving<T> {
    public float size = 1f;
    static final ResourceLocation ENCHANTED_ITEM_GLINT_RES = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    public RenderCancer(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
        LayerShell layerShell = new LayerShell<>(this, ENCHANTED_ITEM_GLINT_RES);
        this.addLayer(layerShell);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        return null;
    }

//    @Override
//    protected void renderLayers(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleIn) {
//        super.renderLayers(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
//    }


    @Override
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
//        float f = (float)entity.ticksExisted + partialTicks;
//        this.bindTexture(ENCHANTED_ITEM_GLINT_RES);
//        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
//        GlStateManager.enableBlend();
//        GlStateManager.depthFunc(514);
//        GlStateManager.depthMask(false);
//        float f1 = 0.5F;
//        GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
//
//        for (int i = 0; i < 2; ++i)
//        {
//            GlStateManager.disableLighting();
//            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
//            float f2 = 0.76F;
//            GlStateManager.color(0.38F, 0.19F, 0.608F, 1.0F);
//            GlStateManager.matrixMode(5890);
//            GlStateManager.loadIdentity();
//            float f3 = 0.33333334F;
//            GlStateManager.scale(0.33333334F, 0.33333334F, 0.33333334F);
//            GlStateManager.rotate(30.0F - (float)i * 60.0F, 0.0F, 0.0F, 1.0F);
//            GlStateManager.translate(0.0F, f * (0.001F + (float)i * 0.003F) * 20.0F, 0.0F);
//            GlStateManager.matrixMode(5888);
//            super.doRender(entity, x, y, z, entityYaw, partialTicks);
//            //model.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale+0.1f);
//            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
//        }
//
//        GlStateManager.matrixMode(5890);
//        GlStateManager.loadIdentity();
//        GlStateManager.matrixMode(5888);
//        GlStateManager.enableLighting();
//        GlStateManager.depthMask(true);
//        GlStateManager.depthFunc(515);
//        GlStateManager.disableBlend();
//        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);

//        renderEnchantedGlint(this, entity, mainModel, entity.limbSwing, entity.limbSwingAmount, partialTicks,
//                entity.ticksExisted,
//                entity.rotationYawHead, entity.rotationPitch, size);

        super.doRender(entity, x, y, z, entityYaw, partialTicks);


//        renderEnchantedGlint(this, entity, mainModel, entity.limbSwing, entity.limbSwingAmount, partialTicks,
//                entity.ticksExisted,
//                entity.rotationYawHead, entity.rotationPitch, size);
    }

    //entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale,
    public static void renderEnchantedGlint(RenderLivingBase<?> p_188364_0_, EntityLivingBase entitylivingbaseIn, ModelBase model, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        float f = (float)entitylivingbaseIn.ticksExisted + partialTicks;
        p_188364_0_.bindTexture(ENCHANTED_ITEM_GLINT_RES);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        GlStateManager.enableBlend();
        GlStateManager.depthFunc(514);
        GlStateManager.depthMask(false);
        float f1 = 0.5F;
        GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);

        for (int i = 0; i < 2; ++i)
        {
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
            float f2 = 0.76F;
            GlStateManager.color(0.38F, 0.19F, 0.608F, 1.0F);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float f3 = 0.33333334F;
            GlStateManager.scale(0.33333334F, 0.33333334F, 0.33333334F);
            GlStateManager.rotate(30.0F - (float)i * 60.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(0.0F, f * (0.001F + (float)i * 0.003F) * 20.0F, 0.0F);
            GlStateManager.matrixMode(5888);
            model.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale+0.1f);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        }

        GlStateManager.matrixMode(5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.enableLighting();
        GlStateManager.depthMask(true);
        GlStateManager.depthFunc(515);
        GlStateManager.disableBlend();
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
    }
}
