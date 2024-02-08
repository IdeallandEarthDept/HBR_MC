package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.potion_effects.ModPotionBase;
import com.deeplake.hbr_mc.potion_effects.ModPotionControl;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class RegisterEffects {
//
//
//    c76bbd38-9ef5-498a-aff9-f80420b9ecb6
//7895524b-dd56-4dbe-833a-b0d217c4c7af
//    de8b270a-dd90-411e-9bd6-789211f3b1c9
//419c11a0-f471-433a-9972-0c9dbe14fd5f
//90bbc8ee-e623-4e11-9884-e24f9a29ca4e
//    bdb62656-f9bb-40ea-95f7-ea2c74a1487d
//    f63a9616-8fea-47a8-8134-7989ab9c4310
//
    public static final List<Potion> INSTANCES = new ArrayList<Potion>();
    public static final ModPotionBase SKILL_ATK_UP_LESSER = new ModPotionBase(false, 0x6abece, "skill_atk_up_1", 0);
    public static final ModPotionBase SKILL_ATK_UP = new ModPotionBase(false, 0x6abece, "skill_atk_up_2", 0);
    public static final ModPotionBase SKILL_ATK_UP_GREATER = new ModPotionBase(false, 0x6abece, "skill_atk_up_3", 0);
    public static final String SELF_RECOIL_UUID = "f3a462f9-9a26-463f-96ce-80f3e5949c65";
    public static final String STUNNED_UUID = "9297cf57-c7ee-4ef0-8e25-3b39a11ed94f";
    public static final ModPotionBase SELF_RECOIL = (ModPotionBase) new ModPotionControl(true, 0x6abece, "self_recoil", 1)
            .registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, SELF_RECOIL_UUID, -1, 2)
            .registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED, SELF_RECOIL_UUID, -1, 2);

    public static final ModPotionBase STUNNED = (ModPotionBase) new ModPotionControl(true, 0xe0e69e, "stunned", 2)
            .registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, STUNNED_UUID, -1, 2)
            .registerPotionAttributeModifier(SharedMonsterAttributes.ATTACK_SPEED, STUNNED_UUID, -1, 2);

    public static final ModPotionBase ANGRY = (ModPotionBase) new ModPotionBase(false, 0xee3333, "angry", 2)
            .setUUID_CLIENT("89a85988-ee18-4ecf-8fc9-331c3d4b86ec");

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> evt)
    {
        evt.getRegistry().registerAll(INSTANCES.toArray(new Potion[0]));
        Main.Log("registered %d potion effect(s)", INSTANCES.size());
    }
}
