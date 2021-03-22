package dev.vchen04.warpbooks.items;

import java.util.Arrays;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.persistence.PersistentDataType;

import dev.vchen04.warpbooks.Plugin;

public class BlankWarpBook extends WarpBook {

    private final BookMeta meta;

    public BlankWarpBook(Plugin plugin) {
        super();
        
        meta = (BookMeta) this.getItemMeta();

        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "type"), PersistentDataType.STRING, "blank");

        meta.setTitle("§bBlank Warp Book");
        meta.setAuthor("§k§l123456789");

        meta.setPages("§k§l12345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912");

        meta.setLore(Arrays.asList(new String[]{
            "",
            "§7A magical book with the power to teleport",
            "§7the user to a location written within.",
            "§7Sneak + Right Click to write a location."
        }));

        this.setItemMeta(meta);

    }

    public static boolean isBlankWarpBook(Plugin plugin, ItemStack item) {

        return (item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "type"), PersistentDataType.STRING) == "blank");
        
    }
    
}
