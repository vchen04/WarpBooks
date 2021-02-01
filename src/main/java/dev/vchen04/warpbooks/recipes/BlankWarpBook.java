package dev.vchen04.warpbooks.recipes;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.BookMeta;

import dev.vchen04.warpbooks.Plugin;

public abstract class BlankWarpBook {
    
    public static void createRecipe(Plugin plugin) {

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();

        bookMeta.setTitle("§bBlank Warp Book");
        bookMeta.setAuthor("§k§l123456789");

        ArrayList<String> pages = new ArrayList<String>();

        // Cover page
        pages.add("§k§l12345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912");

        // JSON data page
        JsonObject bookJsonData = new JsonObject();
        bookJsonData.addProperty("type", "blank");
        pages.add(bookJsonData.toString());

        bookMeta.setPages(pages);

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add("§7A blank magical book with the");
        lore.add("§7power to teleport whoever opens");
        lore.add("§7it to a location written within");
        lore.add("§7Sneak + Right Click to write");
        bookMeta.setLore(lore);

        book.setItemMeta(bookMeta);

        NamespacedKey key = new NamespacedKey(plugin, "blank_warp_book");

        ShapelessRecipe recipe = new ShapelessRecipe(key, book);

        recipe.addIngredient(1, Material.BOOK);
        recipe.addIngredient(1, Material.ENDER_PEARL);

        Bukkit.addRecipe(recipe);

    }

}
