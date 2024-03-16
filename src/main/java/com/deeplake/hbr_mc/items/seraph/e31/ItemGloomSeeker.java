package com.deeplake.hbr_mc.items.seraph.e31;

import com.deeplake.hbr_mc.items.ItemSeraphForNPC;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemGloomSeeker extends ItemSeraphForNPC {
    public ItemGloomSeeker(String name) {
        super(name, EnumSeraphType.SCYTHE);
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null)
                {
                    return 0.0F;
                }
                else
                {
                    return entityIn.getHeldItemMainhand() == stack && entityIn.isSwingInProgress ? 1.0f : 0.0F;
                }
            }
        });
    }
}
