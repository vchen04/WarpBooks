package dev.vchen04.warpbooks.items;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.persistence.PersistentDataType;

import dev.vchen04.warpbooks.Plugin;

public class LocationWarpBook extends WarpBook {

    private final BookMeta meta;

    public LocationWarpBook(Plugin plugin, Location location, String author) {
        super();

        meta = (BookMeta) this.getItemMeta();

        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "type"), PersistentDataType.STRING, "location");
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "world"), PersistentDataType.STRING, location.getWorld().getName());
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "x"), PersistentDataType.DOUBLE, location.getX());
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "y"), PersistentDataType.DOUBLE, location.getY());
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "z"), PersistentDataType.DOUBLE, location.getZ());
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "yaw"), PersistentDataType.FLOAT, location.getYaw());
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "pitch"), PersistentDataType.FLOAT, location.getPitch());

        meta.setTitle("§bLocation Warp Book");
        meta.setAuthor(author);

        meta.setPages(String.format(
                "§k§l12345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912\n\n§r§lWorld\n§r%s\n\n§r§lCoordinates\n§r%d, %d, %d",
                location.getWorld().getName(), Math.round(location.getX()),
                Math.round(location.getY()), Math.round(location.getZ())
            )
        );

        meta.setLore(Arrays.asList(new String[]{
            "",
            "§7A magical book with the power to teleport",
            "§7the user to a location written within.",
            "§7Sneak + Right Click to teleport.",
            "",
            String.format("§7§lWorld §r§7%s", location.getWorld().getName()),
            String.format("§7§lCoordinates §r§7%d, %d, %d", Math.round(location.getX()),
                Math.round(location.getY()), Math.round(location.getZ())
            )
        }));

        this.setItemMeta(meta);

    }

    public static boolean isLocationWarpBook(Plugin plugin, ItemStack item) {

        return (item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(plugin, "type"), PersistentDataType.STRING) == "location");
        
    }

    
}
