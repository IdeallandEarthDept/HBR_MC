package com.deeplake.hbr_mc.proxy;

import com.deeplake.hbr_mc.client.layer.LayerBreak;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;

public class ClientProxy extends ProxyBase{
    @Override
    public void registerLayers() {
        Minecraft.getMinecraft().getRenderManager().entityRenderMap.values().forEach(r -> {
            if (r instanceof RenderLivingBase) {
                ((RenderLivingBase<?>) r).addLayer(new LayerBreak((RenderLivingBase<?>) r));
            }
        });
    }
}
