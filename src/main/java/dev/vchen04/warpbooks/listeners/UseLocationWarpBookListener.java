package dev.vchen04.warpbooks.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Lectern;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import dev.vchen04.warpbooks.Plugin;
import dev.vchen04.warpbooks.items.LocationWarpBook;

public class UseLocationWarpBookListener implements Listener {

    private final Plugin kPlugin;

    public UseLocationWarpBookListener(Plugin plugin) {
        
        kPlugin = plugin;

    }

    private void attemptTeleportPlayer(Player player, ItemStack book) {

        if (LocationWarpBook.isLocationWarpBook(kPlugin, book)) {

            if (player.hasPermission("warpbooks.location.use")) {

                player.teleport(new Location(
                    Bukkit.getWorld(book.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(kPlugin, "world"), PersistentDataType.STRING)),
                    book.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(kPlugin, "x"), PersistentDataType.DOUBLE),
                    book.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(kPlugin, "y"), PersistentDataType.DOUBLE),
                    book.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(kPlugin, "z"), PersistentDataType.DOUBLE)
                ));

            } else {

                player.chat("§cYou do not have permission to write a Location Warp Book");

            }
        }

    }

    @EventHandler
    public void onPlayerInteractBook(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();

        if (action.equals(Action.RIGHT_CLICK_AIR) && player.isSneaking()) {

            if (player.getInventory().getItemInMainHand().getType() == Material.WRITTEN_BOOK) {

                attemptTeleportPlayer(player, player.getInventory().getItemInMainHand());

            }

        } else if (action.equals(Action.RIGHT_CLICK_BLOCK) && player.isSneaking()) {

            if (event.getClickedBlock().getType() == Material.LECTERN) {

                Lectern lectern = (Lectern) event.getClickedBlock().getState();
    
                if (lectern.getInventory().contains(Material.WRITTEN_BOOK)) {

                    attemptTeleportPlayer(player, lectern.getInventory().getItem(lectern.getInventory().first(Material.WRITTEN_BOOK)));

                }

            }

        }

    }

}
