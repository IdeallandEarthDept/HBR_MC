package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.items.ItemArmorCancer;
import com.deeplake.hbr_mc.items.ItemBase;
import com.deeplake.hbr_mc.items.ItemWIP;
import com.deeplake.hbr_mc.items.seraph.a31.*;
import com.deeplake.hbr_mc.items.seraph.g30.ItemKazabana;
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
    public static final Item BRAVE_BLUE_SS = new ItemBraveBlueSS("brave_blue_ss");

    public static final Item RAPID_FIRE = new ItemRapidFire("rapid_fire");
    public static final Item RAPID_FIRE_SS = new ItemRapidFireSS("rapid_fire_ss");

    public static final Item CLAVIS = new ItemWIP("clavis");
    public static final Item CLAVIS_SS = new ItemWIP("clavis_ss");
    
    public static final Item SUPERME_EDGE = new ItemSupremeEdge("superme_edge");
    public static final Item SUPERME_EDGE_SS = new ItemWIP("superme_edge_ss");

    public static final Item GLITTER_SHADOW = new ItemWIP("glitter_shadow");
    public static final Item GLITTER_SHADOW_SS = new ItemWIP("glitter_shadow_ss");

    public static final Item PHANTOM_WEAVER = new ItemWIP("phantom_weaver");
    public static final Item PHANTOM_WEAVER_SS = new ItemWIP("phantom_weaver_ss");

    //31C
    public static final Item SCARLET_VALET = new ItemWIP("scarlet_valet");
    public static final Item SCARLET_VALET_SS = new ItemWIP("scarlet_valet_ss");

    //30G
    public static final Item KAZABANA = new ItemKazabana("kazabana");

    public static final Item GEM_R =       new ItemBase("gem_red");
    public static final Item GEM_Y =       new ItemBase("gem_yellow");
    public static final Item GEM_U =       new ItemBase("gem_blue");
    public static final Item GEM_W =       new ItemBase("gem_white");
    public static final Item GEM_B =       new ItemBase("gem_black");
    public static final Item GEM_AMBER =   new ItemBase("gem_amber");
    public static final Item GEM_DROPLET = new ItemBase("gem_droplet");

    static ItemArmor.ArmorMaterial ARMOR_MATERIAL_CANCER = EnumHelper.addArmorMaterial("cancer", "hbr_mc:cancer", 9999, new int[]{3, 6, 8, 3}, 10, SoundEvents.BLOCK_ANVIL_PLACE, 0);
    public static final Item CANCER_SHELL = new ItemBase("cancer_shell");
    public static final Item CANCER_HELMET = new ItemArmorCancer("cancer_helmet", ARMOR_MATERIAL_CANCER, EntityEquipmentSlot.HEAD);
    public static final Item CANCER_CHESTPLATE = new ItemArmorCancer("cancer_chestplate", ARMOR_MATERIAL_CANCER, EntityEquipmentSlot.CHEST);
    public static final Item CANCER_LEGGINGS = new ItemArmorCancer("cancer_leggings", ARMOR_MATERIAL_CANCER, EntityEquipmentSlot.LEGS);
    public static final Item CANCER_BOOTS = new ItemArmorCancer("cancer_boots", ARMOR_MATERIAL_CANCER, EntityEquipmentSlot.FEET);

    @SubscribeEvent
    public static void handleItem(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ITEM_LIST.toArray(new Item[0]));
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
