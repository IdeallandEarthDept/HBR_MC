package com.deeplake.hbr_mc.entities.cancer;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.init.util.CommonFunctions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class EntityDummyCancer extends EntityCancer{
    public EntityDummyCancer(World worldIn) {
        super(worldIn);
        setAttr(1,0.25f,3,0,1024);
        set6Attr(10);
        setDPMax(9999);
    }

    @Override
    public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {
        //do nothing
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onHurt(LivingHurtEvent evt)
    {
        if (evt.isCanceled())
        {
            return;
        }

        EntityLivingBase livingBase = evt.getEntityLiving();

        if (livingBase instanceof EntityDummyCancer)
        {
            EntityDummyCancer target = (EntityDummyCancer) livingBase;
            if (!target.world.isRemote) {
                if (evt.getEntity() == target)
                {
                    Entity attacker = evt.getSource().getTrueSource();
                    if (attacker instanceof EntityPlayer)
                    {
                        String s = target.getUniqueID().toString();
                        CommonFunctions.SendMsgToPlayer((EntityPlayerMP) attacker, "hbr_mc.msg.dummy_hurt",
                                String.valueOf(evt.getAmount()),
                                s.substring(s.length() - 5),target.getAttr());
                    }
                }
            }
        }

    }
}
