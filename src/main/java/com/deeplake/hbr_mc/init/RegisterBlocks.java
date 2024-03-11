package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.blocks.*;
import com.deeplake.hbr_mc.init.util.CombatUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class RegisterBlocks {

    public static final List<Block> BLOCK_LIST = new ArrayList<>();

    public static final Block HEAL_STASH = new BlockHealStash("heal_stash", Material.ROCK, 5);
    public static final Block HEAL_STASH_PERMA = new BlockHealStash("heal_stash_perma", Material.ROCK, 99).setOneUse(false);
    public static final Block CRYSTAL_ORE = new BlockBase("crystal_ore", Material.ROCK).setHardness(15f);
    public static final Block ENERGY_ORE = new BlockBase("energy_ore", Material.ROCK).setHardness(15f);
    public static final Block CANCER_FLOOR = new BlockCarpetBase("cancer_carpet",Material.IRON).setHardness(4f).setResistance(999f);
    public static final BlockCrate RANDOM_DROP_BASIC = new BlockCrate("random_1", Material.WOOD);
    public static final HashMap<CombatUtil.EnumElement, BlockFieldBase> FIELDS = new HashMap<>();

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        FIELDS.put(CombatUtil.EnumElement.FIRE, new BlockFieldBase("field_fire", CombatUtil.EnumElement.FIRE));
        FIELDS.put(CombatUtil.EnumElement.THUNDER, new BlockFieldBase("field_thunder", CombatUtil.EnumElement.THUNDER));
        FIELDS.put(CombatUtil.EnumElement.ICE, new BlockFieldBase("field_ice", CombatUtil.EnumElement.ICE));
        FIELDS.put(CombatUtil.EnumElement.LIGHT, new BlockFieldBase("field_light", CombatUtil.EnumElement.LIGHT));
        FIELDS.put(CombatUtil.EnumElement.DARK, new BlockFieldBase("field_dark", CombatUtil.EnumElement.DARK));

        event.getRegistry().registerAll(BLOCK_LIST.toArray(new Block[0]));
        CRYSTAL_ORE.setHarvestLevel("pickaxe",3);
        ENERGY_ORE.setHarvestLevel("pickaxe",2);
    }
}
