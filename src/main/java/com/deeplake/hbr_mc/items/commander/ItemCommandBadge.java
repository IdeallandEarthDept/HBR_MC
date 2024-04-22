package com.deeplake.hbr_mc.items.commander;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.entities.ai.idl.EnumActionMode;
import com.deeplake.hbr_mc.entities.npc.EntityNPC;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.deeplake.hbr_mc.items.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;

import static net.minecraft.util.text.translation.I18n.translateToLocalFormatted;

public class ItemCommandBadge extends ItemBase {
    public enum EnumTeam{
        A31,
        B31,
        C31,
        D31,
        E31,
        F31,
        G30,
        X31,
    }

    EnumTeam team;
    public ItemCommandBadge(String name, EnumTeam team) {
        super(name);
        this.team = team;
    }

    int getModeCount()
    {
        return 4;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String modeName = translateToLocalFormatted("item.command_badge.mode." + IDLNBTUtil.GetState(stack));

        return translateToLocalFormatted(this.getUnlocalizedNameInefficiently(stack) + ".name", modeName).trim();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote)
        {
            ItemStack stack = playerIn.getHeldItem(handIn);
            int state = IDLNBTUtil.GetState(stack);
            World world = playerIn.getEntityWorld();
            if (playerIn.isSneaking())
            {
                state = (state + 1) % getModeCount();
                IDLNBTUtil.SetState(stack, state);

                world.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1f, state / 4f +1f);
            }
            else {
                List<EntityNPC> entities = worldIn.getEntitiesWithinAABB(EntityNPC.class, playerIn.getEntityBoundingBox().grow(16.0D));
                world.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1f, state / 4f +1f);
                for (EntityNPC entity : entities) {
                    switch (state) {
                        case 0:
                            entity.setBehaviorMode(EnumActionMode.NONE);
                            entity.setOwner(null);
                            break;
                        case 1:
                            entity.setBehaviorMode(EnumActionMode.DEFEND);
                            entity.setOwner(playerIn);
                            break;
                        case 2:
                            entity.setBehaviorMode(EnumActionMode.ATTACK);
                            entity.setOwner(playerIn);
                            break;
                        case 3:
                            entity.setBehaviorMode(EnumActionMode.FOLLOW);

                            if (entity.getRevengeTarget() != playerIn) {
                                entity.setRevengeTarget(null);
                                entity.setAnger(0);
                            }

                            if (entity.getAttackTarget() != playerIn) {
                                entity.setAttackTarget(null);
                            }
                            entity.setOwner(playerIn);
                            Main.Log("told %s to follow %s", entity.getName(), playerIn.getName());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

}
