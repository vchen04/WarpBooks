package dev.vchen04.warpbooks.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapelessRecipe;

import dev.vchen04.warpbooks.Plugin;
import dev.vchen04.warpbooks.items.BlankWarpBook;

public abstract class BlankWarpBookRecipe {
    
    public static void createRecipe(Plugin plugin) {

        NamespacedKey key = new NamespacedKey(plugin, "blank_warp_book");

        ShapelessRecipe recipe = new ShapelessRecipe(key, new BlankWarpBook(plugin));

        recipe.addIngredient(1, Material.BOOK);
        recipe.addIngredient(1, Material.ENDER_PEARL);

        Bukkit.addRecipe(recipe);

    }

}
