package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.recipes.DropletRefinery;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class RegisterRecipes {

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> evt) {
        IForgeRegistry<IRecipe> r = evt.getRegistry();

        r.register(createMethod(RegisterItem.CANCER_HELMET));
        r.register(createMethod(RegisterItem.CANCER_CHESTPLATE));
        r.register(createMethod(RegisterItem.CANCER_LEGGINGS));
        r.register(createMethod(RegisterItem.CANCER_BOOTS));

    }

    private static ShapelessRecipes createMethod(Item armor) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(Ingredient.fromItem(armor));
        ingredients.add(Ingredient.fromItem(RegisterItem.GEM_DROPLET));
        ShapelessRecipes recipe =
                new DropletRefinery(Main.MODID, new ItemStack(armor), ingredients);
        recipe.setRegistryName(Main.MODID, armor.getUnlocalizedName()+"_refine");
        return recipe;
    }
}
