package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.designs.SeraphBoostConst;
import com.deeplake.hbr_mc.items.ItemWIP;
import com.deeplake.hbr_mc.items.seraph.EnumSeraphRarity;
import com.deeplake.hbr_mc.recipes.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class RegisterRecipes {
    public static final String OREDICT_XPBOOK = "hbr_xp_book";
    public static final int MAX_BOOST_A = 8 + 10;
    public static final int MAX_BOOST_SS = 8 + 10 + 20 + 19;

    public static void registerOreDict()
    {
        OreDictionary.registerOre(OREDICT_XPBOOK,RegisterItem.XP_ITEM_1);
        OreDictionary.registerOre(OREDICT_XPBOOK,RegisterItem.XP_ITEM_2);
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> evt) {
        IForgeRegistry<IRecipe> r = evt.getRegistry();

        r.register(refineryArmor(RegisterItem.CANCER_HELMET));
        r.register(refineryArmor(RegisterItem.CANCER_CHESTPLATE));
        r.register(refineryArmor(RegisterItem.CANCER_LEGGINGS));
        r.register(refineryArmor(RegisterItem.CANCER_BOOTS));

        r.register(socketOpen(RegisterItem.CANCER_HELMET));
        r.register(socketOpen(RegisterItem.CANCER_CHESTPLATE));
        r.register(socketOpen(RegisterItem.CANCER_LEGGINGS));
        r.register(socketOpen(RegisterItem.CANCER_BOOTS));

        getRegister(r, RegisterItem.BRAVE_BLUE, RegisterItem.BRAVE_BLUE_SS);
        getRegister(r, RegisterItem.RAPID_FIRE, RegisterItem.RAPID_FIRE_SS);
        getRegister(r, RegisterItem.CLAVIS, RegisterItem.CLAVIS_SS);
        getRegister(r, RegisterItem.SUPERME_EDGE, RegisterItem.SUPERME_EDGE_SS);
        getRegister(r, RegisterItem.GLITTER_SHADOW, RegisterItem.GLITTER_SHADOW_SS);
        getRegister(r, RegisterItem.PHANTOM_WEAVER, RegisterItem.PHANTOM_WEAVER_SS);
        getRegister(r, RegisterItem.SCARLET_VALET, RegisterItem.SCARLET_VALET_SS);
        getRegister(r, RegisterItem.FATAL_NULL, RegisterItem.FATAL_NULL_SS);
    }

    static int id = 0;
    private static void getRegister(IForgeRegistry<IRecipe> r, Item a, Item ss) {
        if (ss instanceof ItemWIP)
        {
            return;
        }
        id++;
        r.register(new SeraphRankUp(a, ss).setRegistryName(new ResourceLocation(Main.MODID,"up_"+id)));
        r.register(new SeraphDecomp(a, 1).setRegistryName(a.getRegistryName()));
        r.register(new SeraphDecomp(ss, 4).setRegistryName(ss.getRegistryName()));
        r.register(new RecipeXpBookUp(a).setRegistryName(new ResourceLocation(Main.MODID,"xp_"+id)));
        r.register(new RecipeXpBookUp(ss).setRegistryName(new ResourceLocation(Main.MODID,"xp2_"+id)));

        r.register(new RecipeSeraphBoost(a, MAX_BOOST_A).setRegistryName(new ResourceLocation(Main.MODID,"boost"+id)));
        r.register(new RecipeSeraphBoost2(a, MAX_BOOST_A).setRegistryName(new ResourceLocation(Main.MODID,"boost_2"+id)));
        r.register(new SeraphBreakThroughBySame(a, SeraphBoostConst.getMaxBreakThrough(EnumSeraphRarity.A)).setRegistryName(new ResourceLocation(Main.MODID,"brkthru_a_same_"+id)));

        r.register(new RecipeSeraphBoost(ss, MAX_BOOST_SS).setRegistryName(new ResourceLocation(Main.MODID,"boost_ss_"+id)));
        r.register(new RecipeSeraphBoost2(ss, MAX_BOOST_SS).setRegistryName(new ResourceLocation(Main.MODID,"boost_ss_2_"+id)));
        r.register(new SeraphBreakThroughByShard(ss, RegisterItem.SHARD_SS, 5).setRegistryName(new ResourceLocation(Main.MODID,"brkthru_ss_shard_"+id)));
        r.register(new SeraphBreakThroughBySame(ss, SeraphBoostConst.getMaxBreakThrough(EnumSeraphRarity.SS)).setRegistryName(new ResourceLocation(Main.MODID,"brkthru_ss_same_"+id)));

    }

    private static ShapelessRecipes refineryArmor(Item armor) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(Ingredient.fromItem(armor));
        ingredients.add(Ingredient.fromItem(RegisterItem.GEM_DROPLET));
        ShapelessRecipes recipe =
                new DropletRefinery(Main.MODID, new ItemStack(armor), ingredients);
        recipe.setRegistryName(Main.MODID, armor.getUnlocalizedName()+"_refine");
        return recipe;
    }

    private static ShapelessRecipes socketOpen(Item armor) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(Ingredient.fromItem(armor));
        ingredients.add(Ingredient.fromItem(RegisterItem.GEM_AMBER));
        ShapelessRecipes recipe =
                new DropletRefinery(Main.MODID, new ItemStack(armor), ingredients);
        recipe.setRegistryName(Main.MODID, armor.getUnlocalizedName()+"_socket");
        return recipe;
    }
}
