package com.deeplake.hbr_mc.items;

import com.deeplake.hbr_mc.ModTabs;
import com.deeplake.hbr_mc.entities.EntityBase;
import com.deeplake.hbr_mc.entities.cancer.EntityCancer;
import com.deeplake.hbr_mc.entities.cancer.EntityDummyCancer;
import com.deeplake.hbr_mc.init.RegisterUtil;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemSpawnTestCancer extends ItemMonsterPlacer {
    public ItemSpawnTestCancer(String name) {
        super();
        RegisterUtil.initItem(this, name);
        setCreativeTab(ModTabs.TAB_EGG);
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (worldIn.isRemote)
        {
            return EnumActionResult.SUCCESS;
        }
        else if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (block == Blocks.MOB_SPAWNER)
            {
                TileEntity tileentity = worldIn.getTileEntity(pos);

                if (tileentity instanceof TileEntityMobSpawner)
                {
                    MobSpawnerBaseLogic mobspawnerbaselogic = ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic();
                    mobspawnerbaselogic.setEntityId(getNamedIdFrom(itemstack));
                    tileentity.markDirty();
                    worldIn.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);

                    if (!player.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);
                    }

                    return EnumActionResult.SUCCESS;
                }
            }

            BlockPos blockpos = pos.offset(facing);
            double d0 = this.getYOffset(worldIn, blockpos);
            Entity entity = spawnCreature(worldIn, getNamedIdFrom(itemstack), (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + d0, (double)blockpos.getZ() + 0.5D);

            if (entity != null)
            {
                if (entity instanceof EntityLivingBase && itemstack.hasDisplayName())
                {
                    entity.setCustomNameTag(itemstack.getDisplayName());
                }
                else if (entity instanceof EntityDummyCancer){
                    entity.setCustomNameTag(entity.getName()+IDLNBTUtil.GetState(itemstack));
                }

                applyItemEntityDataToEntity(worldIn, player, itemstack, entity);

                if (entity instanceof EntityBase)
                {
                    handleAttr(itemstack, (EntityBase)entity);
                }

                if (!player.capabilities.isCreativeMode)
                {
                    itemstack.shrink(1);
                }
            }

            return EnumActionResult.SUCCESS;
        }
    }

    public void handleAttr(ItemStack stack, EntityBase livingBase){
        livingBase.set6Attr(IDLNBTUtil.GetState(stack));
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            for (EntityList.EntityEggInfo eggInfo : EntityList.ENTITY_EGGS.values())
            {
                EntityEntry entry = ForgeRegistries.ENTITIES.getValue(eggInfo.spawnedID);

                if (entry != null && EntityCancer.class.isAssignableFrom(entry.getEntityClass())) {
                    ItemStack itemstack = new ItemStack(this, 1);
                    applyEntityIdToItemStack(itemstack, eggInfo.spawnedID);
                    for (int attr = 50; attr < 500; attr+=50) {
                        IDLNBTUtil.SetState(itemstack, attr);
                        items.add(itemstack);
                        itemstack = itemstack.copy();
                    }
                    items.add(ItemStack.EMPTY);
                }
            }
        }
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        String s = ("" + I18n.translateToLocalFormatted(this.getUnlocalizedName() + ".name",
                IDLNBTUtil.GetState(stack))).trim();
        String s1 = EntityList.getTranslationName(getNamedIdFrom(stack));

        if (s1 != null)
        {
            s = s + " " + I18n.translateToLocal("entity." + s1 + ".name");
        }

        return s;
    }
}
