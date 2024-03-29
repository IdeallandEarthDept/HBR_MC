package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.blocks.BlockBase;
import com.deeplake.hbr_mc.blocks.BlockCarpetBase;
import com.deeplake.hbr_mc.blocks.BlockCrate;
import com.deeplake.hbr_mc.blocks.BlockHealStash;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
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

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(BLOCK_LIST.toArray(new Block[0]));
        CRYSTAL_ORE.setHarvestLevel("pickaxe",3);
        ENERGY_ORE.setHarvestLevel("pickaxe",2);
    }
}
