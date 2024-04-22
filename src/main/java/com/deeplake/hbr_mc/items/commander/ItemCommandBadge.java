package com.deeplake.hbr_mc.items.commander;

import com.deeplake.hbr_mc.entities.npc.EntityNPC;
import com.deeplake.hbr_mc.init.util.IDLNBTUtil;
import com.deeplake.hbr_mc.items.ItemBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

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

    int getMaxMode()
    {
        return 3;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        player.setActiveHand(hand);
        ItemStack stack = player.getHeldItem(hand);
        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
    }

    //on item use
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        List<EntityNPC> entities = worldIn.getEntitiesWithinAABB(EntityNPC.class, entityLiving.getEntityBoundingBox().grow(16.0D));
        int state = IDLNBTUtil.GetState(stack);
        //0: idle
        //1: follow
        //2: attack
        for (EntityNPC entity : entities) {
            switch (state) {
                case 0:

                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:
                    break;
            }
        }

        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
