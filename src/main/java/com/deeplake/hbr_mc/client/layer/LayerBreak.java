package com.deeplake.hbr_mc.client.layer;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.util.DShieldUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerBreak implements LayerRenderer<EntityLivingBase> {
    private final RenderLivingBase<?> renderer;
    public static ResourceLocation OVERLAY_ROCK = new ResourceLocation(Main.MODID,"textures/entity/cancer_break.png");
    public static ResourceLocation OVERLAY_LIGHT = new ResourceLocation(Main.MODID,"textures/entity/cancer_break.png");

    public LayerBreak(RenderLivingBase<?> rendererIn)
    {
        this.renderer = rendererIn;
    }

    @Override
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        int i = 30;
        doRenderLayerPetrify(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public void doRenderLayerPetrify(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (!DShieldUtil.isDPDepleted(entitylivingbaseIn))
        {
            return;
        }

        //this.renderer.bindTexture(OVERLAY_ROCK);
        //this.renderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        //light
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!entitylivingbaseIn.isInvisible());
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680.0F, 0.0F);
        GlStateManager.enableLighting();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        this.renderer.bindTexture(OVERLAY_LIGHT);
        this.renderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);

        if (renderer instanceof RenderLiving && entitylivingbaseIn instanceof EntityLiving)
        {
            ((RenderLiving)renderer).setLightmap((EntityLiving) entitylivingbaseIn);
        }
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
