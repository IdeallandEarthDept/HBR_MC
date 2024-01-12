package com.deeplake.hbr_mc.client.renderer.layer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static com.deeplake.hbr_mc.client.renderer.cancer.RenderCancer.renderEnchantedGlint;

public class LayerShell<T extends ModelBase> implements LayerRenderer<EntityLivingBase> {
    private final RenderLivingBase<?> renderer;
    ResourceLocation location;

    public LayerShell(RenderLivingBase<?> rendererIn, ResourceLocation location) {
        this.renderer = rendererIn;
        this.location = location;
    }

    private void renderArmorLayer(EntityLivingBase entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        EntityEquipmentSlot slotIn = EntityEquipmentSlot.CHEST;
        ItemStack itemstack = entityLivingBaseIn.getItemStackFromSlot(slotIn);

//        if (itemstack.getItem() instanceof ItemArmor)
//        {
//            ItemArmor itemarmor = (ItemArmor)itemstack.getItem();
//
//            if (itemarmor.getEquipmentSlot() == slotIn)
//            {
                T t = (T) this.renderer.getMainModel();
                //t = (T) getArmorModelHook(entityLivingBaseIn, itemstack, slotIn, t);
                t.setModelAttributes(this.renderer.getMainModel());
                t.setLivingAnimations(entityLivingBaseIn, limbSwing, limbSwingAmount, partialTicks);
                //this.setModelSlotVisible(t, slotIn);
                //boolean flag = this.isLegSlot(slotIn);
                this.renderer.bindTexture(location);

                {
                    { // Non-colored
                        GlStateManager.color(1f, 1f, 1f, 1f);
                        t.render(entityLivingBaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                    } // Default

                    renderEnchantedGlint(this.renderer, entityLivingBaseIn, t, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
                }
//            }
//        }
    }


    @Override
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        renderArmorLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
