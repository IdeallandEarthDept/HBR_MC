package com.deeplake.hbr_mc.init;

import com.deeplake.hbr_mc.Main;
import com.deeplake.hbr_mc.recipes.DropletRefinery;
import com.deeplake.hbr_mc.recipes.SeraphRankUp;
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

@Mod.EventBusSubscriber(modid = Main.MODID)
public class RegisterRecipes {

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> evt) {
        IForgeRegistry<IRecipe> r = evt.getRegistry();

        r.register(craftArmor(RegisterItem.CANCER_HELMET));
        r.register(craftArmor(RegisterItem.CANCER_CHESTPLATE));
        r.register(craftArmor(RegisterItem.CANCER_LEGGINGS));
        r.register(craftArmor(RegisterItem.CANCER_BOOTS));

        getRegister(r, RegisterItem.BRAVE_BLUE, RegisterItem.BRAVE_BLUE_SS);
        getRegister(r, RegisterItem.RAPID_FIRE, RegisterItem.RAPID_FIRE_SS);
        getRegister(r, RegisterItem.CLAVIS, RegisterItem.CLAVIS_SS);
        getRegister(r, RegisterItem.SUPERME_EDGE, RegisterItem.SUPERME_EDGE_SS);
        getRegister(r, RegisterItem.GLITTER_SHADOW, RegisterItem.GLITTER_SHADOW_SS);
        getRegister(r, RegisterItem.PHANTOM_WEAVER, RegisterItem.PHANTOM_WEAVER_SS);
        getRegister(r, RegisterItem.SCARLET_VALET, RegisterItem.SCARLET_VALET_SS);
    }

    private static void getRegister(IForgeRegistry<IRecipe> r, Item a, Item ss) {
        r.register(new SeraphRankUp(a, ss).setRegistryName(ss.getRegistryName()));
    }

    private static ShapelessRecipes craftArmor(Item armor) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(Ingredient.fromItem(armor));
        ingredients.add(Ingredient.fromItem(RegisterItem.GEM_DROPLET));
        ShapelessRecipes recipe =
                new DropletRefinery(Main.MODID, new ItemStack(armor), ingredients);
        recipe.setRegistryName(Main.MODID, armor.getUnlocalizedName()+"_refine");
        return recipe;
    }
}
