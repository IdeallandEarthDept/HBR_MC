package com.deeplake.hbr_mc.client.renderer;

import com.deeplake.hbr_mc.client.layer.LayerBipedGlass;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHumanoidGlassShell extends RenderHumanoidGlowShell{
    protected ResourceLocation RES_LOC_GLASS;

    public RenderHumanoidGlassShell(RenderManager renderManagerIn) {
        super(renderManagerIn);
        setGlowTexture();
    }

    public RenderHumanoidGlassShell(RenderManager renderManagerIn, String TexturePath) {
        super(renderManagerIn, TexturePath);
        setGlowTexture();
    }

    public RenderHumanoidGlassShell(RenderManager renderManagerIn, String TexturePath, float scale) {
        super(renderManagerIn, TexturePath, scale);
        setGlowTexture();
    }

    void setGlowTexture()
    {
        String resourcePath = RES_LOC.getResourcePath();
        RES_LOC_GLASS = new ResourceLocation(RES_LOC.getResourceDomain(), resourcePath.substring(0,resourcePath.length()-4) + "_glass.png");
        this.addLayer(new LayerBipedGlass(this, RES_LOC_GLASS));
        super.setGlowTexture();
    }
}
