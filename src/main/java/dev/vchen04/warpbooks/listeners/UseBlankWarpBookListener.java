package dev.vchen04.warpbooks.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import dev.vchen04.warpbooks.Plugin;
import dev.vchen04.warpbooks.items.BlankWarpBook;
import dev.vchen04.warpbooks.items.LocationWarpBook;

public class UseBlankWarpBookListener implements Listener {

    private final Plugin kPlugin;

    public UseBlankWarpBookListener(Plugin plugin) {

        kPlugin = plugin;

    }

    @EventHandler
    public void onPlayerInteractBook(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();

        if ( BlankWarpBook.isBlankWarpBook(kPlugin, player.getInventory().getItemInMainHand()) && ((action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR)) && player.isSneaking())) {

            if (player.hasPermission("warpbooks.location.write")) {

                player.getInventory().setItemInMainHand(new LocationWarpBook(kPlugin, player.getLocation(), player.getName()));

            } else {

                player.chat("§cYou do not have permission to write a Location Warp Book");

            }

        }

    }

}
