package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.potion_effects.ModPotionBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class RegisterEffects {
    public static final List<Potion> INSTANCES = new ArrayList<Potion>();
    public static final ModPotionBase SKILL_ATK_UP_LESSER = new ModPotionBase(false, 0x6abece, "skill_atk_up_1", 0);
    public static final ModPotionBase SKILL_ATK_UP = new ModPotionBase(false, 0x6abece, "skill_atk_up_2", 0);
    public static final ModPotionBase SKILL_ATK_UP_GREATER = new ModPotionBase(false, 0x6abece, "skill_atk_up_3", 0);

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> evt)
    {
        evt.getRegistry().registerAll(INSTANCES.toArray(new Potion[0]));
        Main.Log("registered %d potion effect(s)", INSTANCES.size());
    }
}
