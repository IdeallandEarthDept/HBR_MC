package com.deeplake.hbr_mc.client.renderer;

import com.deeplake.hbr_mc.client.layer.LayerBipedGlow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHumanoidGlowShell extends RenderHumanoid{
    protected ResourceLocation RES_LOC_GLOW;

    public RenderHumanoidGlowShell(RenderManager renderManagerIn) {
        super(renderManagerIn);
        setGlowTexture();
    }

    public RenderHumanoidGlowShell(RenderManager renderManagerIn, String TexturePath) {
        super(renderManagerIn, TexturePath);
        setGlowTexture();
    }

    public RenderHumanoidGlowShell(RenderManager renderManagerIn, String TexturePath, float scale) {
        super(renderManagerIn, TexturePath, scale);
        setGlowTexture();
    }

    void setGlowTexture()
    {
        String resourcePath = RES_LOC.getResourcePath();
        RES_LOC_GLOW = new ResourceLocation(RES_LOC.getResourceDomain(), resourcePath.substring(0,resourcePath.length()-4) + "_glow.png");
        this.addLayer(new LayerBipedGlow(this, RES_LOC_GLOW));
    }
}
