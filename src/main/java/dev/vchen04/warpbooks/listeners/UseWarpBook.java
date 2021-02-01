package dev.vchen04.warpbooks.listeners;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Lectern;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class UseWarpBook implements Listener {

    final Gson GSON = new Gson();

    private void teleportPlayer(Player player, ItemStack book) {

        BookMeta bookMeta = (BookMeta) book.getItemMeta(); // Should fail if the item is not a book

        // It should be impossible to set the title of a book to §bWarp Book, so even if someone crafts a JSON string
        // and saves it in a book, it should not be able to be used.
        if (bookMeta.getTitle().equals("§bWarp Book")) {

            if (player.hasPermission("warpbooks.use")) {

                JsonObject bookJsonData = GSON.fromJson(bookMeta.getPage(2), JsonObject.class);

                // Should fail if there is no position data
                player.teleport(new Location(Bukkit.getWorld(bookJsonData.get("world").getAsString()),
                        bookJsonData.get("x").getAsDouble(), bookJsonData.get("y").getAsDouble(),
                        bookJsonData.get("z").getAsDouble(), bookJsonData.get("yaw").getAsFloat(),
                        bookJsonData.get("pitch").getAsFloat()));

            } else {

                player.chat("§cYou do not have permission to use a Warp Book");

            }

        }

    }

    @EventHandler
    public void onPlayerInteractBook(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();

        if ((action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR)) && player.isSneaking()) {

            try {

                teleportPlayer(player, player.getInventory().getItemInMainHand());

            } catch (Exception e) {

            }

            try {

                Lectern lectern = (Lectern) event.getClickedBlock().getState();

                teleportPlayer(player,
                        lectern.getInventory().getItem(lectern.getInventory().first(Material.WRITTEN_BOOK)));

            } catch (Exception e) {

            }

        }

    }

}
