package com.deeplake.hbr_mc.items.seraph;

import com.deeplake.hbr_mc.ModTabs;
import com.deeplake.hbr_mc.init.RegisterUtil;
import com.deeplake.hbr_mc.init.util.IDLNBTDef;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;

import java.util.UUID;

public class ItemSeraphBase extends ItemSword {
    UUID uuid = UUID.fromString("7cf25a1c-6768-4836-8d24-ec64ed2a4ef7");
    public ItemSeraphBase(String name) {
        super(ToolMaterial.DIAMOND);
        RegisterUtil.initItem(this, name);
        setCreativeTab(ModTabs.TAB1);
        setMaxStackSize(1);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return oldStack == null || newStack == null || slotChanged || oldStack.getItemDamage() != newStack.getItemDamage();
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, IBlockAccess world, BlockPos pos, EntityPlayer player) {
        return false;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    public int getRGBDurabilityForDisplay(ItemStack stack)
    {
        return MathHelper.hsvToRGB(Math.max(0.0F, (float) (1.5F + getDurabilityForDisplay(stack))) / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    //    public boolean canDisableShield(ItemStack stack, ItemStack shield, EntityLivingBase entity, EntityLivingBase attacker)
//    {
//        return this instanceof ItemAxe;
//    }
//
//    /**
//     * Is this Item a shield
//     * @param stack The ItemStack
//     * @param entity The Entity holding the ItemStack
//     * @return True if the ItemStack is considered a shield
//     */
//    public boolean isShield(ItemStack stack, @Nullable EntityLivingBase entity)
//    {
//        return stack.getItem() == Items.SHIELD;
//    }

    //onItemUseFirst?
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        boolean success = false;
        Vec3d startPos = player.getPositionVector();
        ItemStack stack = player.getHeldItem(hand);
        if (SeraphUtil.isBroken(stack))
        {
            return EnumActionResult.FAIL;
        }
        if (facing.getAxis() == EnumFacing.Axis.Y)
        {
            //teleport forward if player is not sneaking, otherwise teleport backward
            float distance = 5;
            Vec3d lookVecXZ = new Vec3d(player.getLookVec().x, 0, player.getLookVec().z).normalize();
            if (!player.isSneaking())
            {
                success = player.attemptTeleport(player.posX + lookVecXZ.x * distance, player.posY + 1, player.posZ + lookVecXZ.z * distance);
            }
            else
            {
                success = player.attemptTeleport(player.posX - lookVecXZ.x * distance, player.posY + 1, player.posZ - lookVecXZ.z * distance);
            }

        }
        else {
            //If right clicked on the side of a wall, teleport onto the top of it if possible
            //Max Y movement is 10 blocks
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(pos);
            for (int dy = 0; dy <= 10; dy++)
            {
                int y = player.getPosition().getY();
                mutableBlockPos.setY(y + dy);
                if (worldIn.isAirBlock(mutableBlockPos))
                {
                    if (player.attemptTeleport(mutableBlockPos.getX()+0.5f, mutableBlockPos.getY(), mutableBlockPos.getZ()+0.5f))
                    {
                        success = true;
                        break;
                    }

                }
            }
        }

        if (success)
        {
            if (!worldIn.isRemote)
            {
                player.getCooldownTracker().setCooldown(this, 5);
                int distance = (int) startPos.distanceTo(player.getPositionVector());
                stack.damageItem(distance, player);
            }
            return EnumActionResult.SUCCESS;
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 1024;
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        if (damage > getMaxDamage(stack))
        {
            damage = getMaxDamage(stack);
            boolean isBroken = SeraphUtil.isBroken(stack);
            if (!isBroken)
            {
                IDLNBTUtil.SetBoolean(stack, IDLNBTDef.KEY_BROKEN, true);
            }
        }
        super.setDamage(stack, damage);
    }

//    @Override
//    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
//        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
//        if (!worldIn.isRemote)
//        {
//            if (SeraphUtil.isBroken(stack))
//            {
//                if (worldIn.getTotalWorldTime() % 24000 == 0)
//                {
//                    //todo: chance to repair
//                }
//                return;
//            }
//        }
//    }


    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = HashMultimap.<String, AttributeModifier>create();

        if (slot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", SeraphUtil.getLevel(stack)+3, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
        }

        return super.getAttributeModifiers(slot, stack);
    }
}
