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

public class UseWarpBookListener implements Listener {

    final Gson gson = new Gson();

    private void teleportPlayer(Player player, ItemStack book) {

        BookMeta bookMeta = (BookMeta) book.getItemMeta();

        JsonObject bookPositionData = gson.fromJson(bookMeta.getPage(2), JsonObject.class);

        player.teleport(new Location(Bukkit.getWorld(bookPositionData.get("world").getAsString()),
                bookPositionData.get("x").getAsDouble(), bookPositionData.get("y").getAsDouble(),
                bookPositionData.get("z").getAsDouble(), bookPositionData.get("yaw").getAsFloat(),
                bookPositionData.get("pitch").getAsFloat()));
        
    }
    
    @EventHandler
    public void onPlayerInteractBook(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();

        if ( ( action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR) ) && player.isSneaking() ) {

            try {

                teleportPlayer(player, player.getInventory().getItemInMainHand());

            }

            catch (Exception e) {

            }

            try {
                
                Lectern lectern = (Lectern) event.getClickedBlock().getState();

                teleportPlayer(player, lectern.getInventory().getItem(lectern.getInventory().first(Material.WRITTEN_BOOK)));

            }

            catch (Exception e) {

            }

        }

    }

}
