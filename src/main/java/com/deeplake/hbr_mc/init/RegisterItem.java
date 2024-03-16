package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.items.*;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphType;
import com.deeplake.hbr_mc.items.seraph.a31.*;
import com.deeplake.hbr_mc.items.seraph.b31.ItemFatalNull;
import com.deeplake.hbr_mc.items.seraph.b31.ItemFatalNullSS;
import com.deeplake.hbr_mc.items.seraph.b31.ItemMana;
import com.deeplake.hbr_mc.items.seraph.e31.ItemGloomSeeker;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class RegisterItem {
    public static final List<Item> ITEM_LIST = new ArrayList<>();
    //31A
    public static final Item BRAVE_BLUE = new ItemBraveBlue("brave_blue");
    public static final Item BRAVE_BLUE_S = new ItemSeraphForNPC("brave_blue_s", EnumSeraphType.DOUBLE_SWORD);
    public static final Item BRAVE_BLUE_SS = new ItemBraveBlueSS("brave_blue_ss");

    public static final Item RAPID_FIRE = new ItemRapidFire("rapid_fire");
    public static final Item RAPID_FIRE_S = new ItemWIPRanged("rapid_fire_s", EnumSeraphType.GUN);
    public static final Item RAPID_FIRE_SS = new ItemRapidFireSS("rapid_fire_ss");

    public static final Item CLAVIS = new ItemClavis("clavis");
    public static final Item CLAVIS_S = new ItemSeraphForNPC("clavis_s", EnumSeraphType.LARGE_SWORD);
    public static final Item CLAVIS_SS = new ItemClavisSS("clavis_ss");
    
    public static final Item SUPERME_EDGE = new ItemSupremeEdge("supreme_edge");
    public static final Item SUPERME_EDGE_S = new ItemWIPRanged("supreme_edge_s",EnumSeraphType.GUN);
    public static final Item SUPERME_EDGE_SS = new ItemWIPRanged("supreme_edge_ss",EnumSeraphType.GUN);

    public static final Item GLITTER_SHADOW = new ItemGlitterShadowA("glitter_shadow");
    public static final Item GLITTER_SHADOW_S = new ItemSeraphForNPC("glitter_shadow_s", EnumSeraphType.SCYTHE);
    public static final Item GLITTER_SHADOW_SS = new ItemGlitterShadowSS("glitter_shadow_ss");

    public static final Item PHANTOM_WEAVER = new ItemPhantomWeaver("phantom_weaver");
    public static final Item PHANTOM_WEAVER_S = new ItemSeraphForNPC("phantom_weaver_s", EnumSeraphType.SWORD);
    public static final Item PHANTOM_WEAVER_SS = new ItemPhantomWeaverSS("phantom_weaver_ss");

    //31B
    public static final Item MANA = new ItemMana("mana");
    public static final Item MANA_S = new ItemSeraphForNPC("mana_s", EnumSeraphType.SCYTHE);
    public static final Item MANA_SS = new ItemSeraphForNPC("mana_ss", EnumSeraphType.SCYTHE);//not obtained,skip
    public static final Item FATAL_NULL = new ItemFatalNull("fatal_null");
    public static final Item FATAL_NULL_SS = new ItemFatalNullSS("fatal_null_ss");
    public static final Item RAINNY_LULL = new ItemSeraphForNPC("rainny_lull", EnumSeraphType.SCYTHE);
    public static final Item RAINNY_LULL_S = new ItemSeraphForNPC("rainny_lull_s", EnumSeraphType.SCYTHE);
    public static final Item RAINNY_LULL_SS = new ItemSeraphForNPC("rainny_lull_ss", EnumSeraphType.SCYTHE);


    //31C
    public static final Item SCARLET_VALET = new ItemWIPRanged("scarlet_valet", EnumSeraphType.GUN);
    public static final Item SCARLET_VALET_S = new ItemWIPRanged("scarlet_valet_s", EnumSeraphType.GUN);
    public static final Item SCARLET_VALET_SS = new ItemWIPRanged("scarlet_valet_ss", EnumSeraphType.GUN);

    //30G
    public static final Item KAZABANA = new ItemWIPRanged("kazabana", EnumSeraphType.GUN);

//    public static final Item NUE = new ItemSeraphForNPC("nue", EnumSeraphType.LARGE_SWORD);
//    public static final Item NUE_S = new ItemSeraphForNPC("nue_s", EnumSeraphType.LARGE_SWORD);
//    public static final Item NUE_SS = new ItemSeraphForNPC("nue_ss", EnumSeraphType.LARGE_SWORD);

    //31E
    public static final Item FLAVOR_RAIN = new ItemWIPRanged("flavor_rain", EnumSeraphType.GUN);
    public static final Item GLOOM_SEEKER = new ItemGloomSeeker("gloom_seeker");

    //31F
    public static final Item LUXURY_THORN = new ItemWIPRanged("luxury_thorn", EnumSeraphType.GUN);
    public static final Item CRY_FLOWER = new ItemSeraphForNPC("cry_flower", EnumSeraphType.CANNON);
    public static final Item FURY_STRINGER = new ItemSeraphForNPC("fury_stringer", EnumSeraphType.SCYTHE);
    public static final Item POP_N_GORE = new ItemSeraphForNPC("pop_n_gore", EnumSeraphType.SHIELD);
    public static final Item REN = new ItemSeraphForNPC("ren", EnumSeraphType.SWORD);
    public static final Item AMAZING_EXECUTER = new ItemSeraphForNPC("amazing_executer", EnumSeraphType.LARGE_SWORD);

    //31X
    public static final Item IRON_MAIDEN = new ItemSeraphForNPC("iron_maiden", EnumSeraphType.LARGE_SWORD);
    public static final Item IRON_MAIDEN_S = new ItemSeraphForNPC("iron_maiden_s", EnumSeraphType.LARGE_SWORD);

    public static final Item BLOODY_SAGE = new ItemSeraphForNPC("bloody_sage", EnumSeraphType.LARGE_SWORD);
    public static final Item BLOODY_SAGE_S = new ItemSeraphForNPC("bloody_sage_s", EnumSeraphType.LARGE_SWORD);
    public static final Item BLOODY_SAGE_SS = new ItemSeraphForNPC("bloody_sage_ss", EnumSeraphType.LARGE_SWORD);

    public static final Item INNOCENT_STINGER = new ItemWIPRanged("innocent_stinger", EnumSeraphType.GUN);
    public static final Item INFINITE = new ItemSeraphForNPC("infinite", EnumSeraphType.SHIELD);
    public static final Item CITADEL_OF_WISDOM = new ItemWIPRanged("citadel_of_wisdom", EnumSeraphType.GUN);
    public static final Item TACTICAL_WHEEL = new ItemSeraphForNPC("tactical_wheel", EnumSeraphType.SWORD);

    public static final Item GEM_R =       new ItemBase("gem_red");
    public static final Item GEM_Y =       new ItemBase("gem_yellow");
    public static final Item GEM_U =       new ItemBase("gem_blue");
    public static final Item GEM_W =       new ItemBase("gem_white");
    public static final Item GEM_B =       new ItemBase("gem_black");
    public static final Item GEM_AMBER =   new ItemBase("gem_amber");

    public static final Item BOOST_1_A =   new ItemBase("boost_1_a");
    public static final Item BOOST_1_B =   new ItemBase("boost_1_b");
    public static final Item BOOST_1_C =   new ItemBase("boost_1_c");
    public static final Item GEM_DROPLET = new ItemBase("gem_droplet");
    public static final Item SHARD_SS = new ItemBase("shard_ss");
    public static final Item GEM_LOTTERY = new ItemBase("crystal_lottery");
    public static final Item LOTTERY = new ItemLottery("lottery");
    public static final Item XP_ITEM_1 = new ItemXpBook("xp_item_1",500);
    public static final Item XP_ITEM_2 = new ItemXpBook("xp_item_2",2000);
    public static final Item LEAF_CIRCLET = new ItemCirclet("leaf_circlet");

    static ItemArmor.ArmorMaterial ARMOR_MATERIAL_CANCER = EnumHelper.addArmorMaterial("cancer", "hbr_mc:cancer", 9999, new int[]{3, 6, 8, 3}, 10, SoundEvents.BLOCK_ANVIL_PLACE, 0);
    public static final Item CANCER_SHELL = new ItemBase("cancer_shell");
    public static final Item CANCER_HELMET = new ItemArmorCancer("cancer_helmet", ARMOR_MATERIAL_CANCER, EntityEquipmentSlot.HEAD);
    public static final Item CANCER_CHESTPLATE = new ItemArmorCancer("cancer_chestplate", ARMOR_MATERIAL_CANCER, EntityEquipmentSlot.CHEST);
    public static final Item CANCER_LEGGINGS = new ItemArmorCancer("cancer_leggings", ARMOR_MATERIAL_CANCER, EntityEquipmentSlot.LEGS);
    public static final Item CANCER_BOOTS = new ItemArmorCancer("cancer_boots", ARMOR_MATERIAL_CANCER, EntityEquipmentSlot.FEET);
    public static final Item BETTER_SPAWNER = new ItemSpawnTestCancer("accurate_spawner");

    @SubscribeEvent
    public static void handleItem(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ITEM_LIST.toArray(new Item[0]));
        RegisterRecipes.registerOreDict();
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event)
    {
        for (Item item:
             ITEM_LIST) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
}
