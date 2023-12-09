package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashSet;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class RegisterAttr {
    static final double MIN = -9999999;
    static final double MAX = 999999f;

    public static final HashSet<IAttribute> allNewAttrs = new HashSet<>();

    public static final IAttribute STR = getNewAttrNonPercent("str", 5);
    public static final IAttribute DEX = getNewAttrNonPercent("dex", 5);
    public static final IAttribute END = getNewAttrNonPercent("end", 5);
    public static final IAttribute MEN = getNewAttrNonPercent("men", 5);
    public static final IAttribute INT = getNewAttrNonPercent("int", 5);
    public static final IAttribute LUC = getNewAttrNonPercent("luc", 5);

    @SubscribeEvent
    public static void onConstruct(EntityEvent.EntityConstructing entityConstructing)
    {
        //note that this happens in the last of Entity::ctor
        //any super class applies their attr after this.
        Entity entity = entityConstructing.getEntity();
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase livingBase = (EntityLivingBase) entity;
            for (IAttribute attr:
                    allNewAttrs) {
                livingBase.getAttributeMap().registerAttribute(attr);
            }

            if (!(livingBase instanceof EntityPlayer)) {
                addIfMissingAttr(livingBase, SharedMonsterAttributes.ATTACK_SPEED);
            }
            //this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        }
    }

    private static void addIfMissingAttr(EntityLivingBase livingBase, IAttribute attribute) {
        if (livingBase.getEntityAttribute(attribute) == null)
        {
            livingBase.getAttributeMap().registerAttribute(attribute);
        }
    }

    //"description" is merely another matching name.
    //used by net.minecraft.entity.ai.attributes.AttributeMap::getAttributeInstanceByName
    //which is used in some places. It is not intended for human reading nor translation, more like a key.
    public static IAttribute getNewAttr(String name)
    {
        IAttribute attribute = new RangedAttribute(null, getAttrName(name), 0, MIN, MAX).setDescription(name).setShouldWatch(false);
        allNewAttrs.add(attribute);
        return attribute;
    }

    public static IAttribute getNewAttrNonPercent(String name)
    {
        IAttribute attribute = new RangedAttribute(null, getAttrName(name), 0, MIN, MAX).setDescription(name).setShouldWatch(false);
        allNewAttrs.add(attribute);
        return attribute;
    }

    public static IAttribute getNewAttrNonPercent(String name, double defaultVal)
    {
        IAttribute attribute = new RangedAttribute(null, getAttrName(name), defaultVal, MIN, MAX).setDescription(name).setShouldWatch(false);
        allNewAttrs.add(attribute);
        return attribute;
    }

    static final String MID_NAME = ".attr.";
    static String getAttrName(String name){
        return Main.MODID + MID_NAME + name;
    }
}
