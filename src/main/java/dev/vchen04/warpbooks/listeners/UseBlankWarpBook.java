package dev.vchen04.warpbooks.listeners;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class UseBlankWarpBook implements Listener {

    final Gson GSON = new Gson();

    @EventHandler
    public void onPlayerInteractBook(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();

        if ((action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR)) && player.isSneaking()) {

            try {

                ItemStack item = player.getInventory().getItemInMainHand();

                BookMeta itemMeta = (BookMeta) item.getItemMeta(); // Should fail if the item is not a book

                // It should be impossible to set the title of a book to §bBlank Warp Book, so even if someone crafts a JSON string
                // and saves it in a book, it should not be able to be used.
                if (itemMeta.getTitle().equals("§bBlank Warp Book") && GSON
                        .fromJson(itemMeta.getPage(2), JsonObject.class).get("type").getAsString().equals("blank")) {

                    if (player.hasPermission("warpbooks.write")) {

                        // Create the book item that will become the warp book
                        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                        BookMeta bookMeta = (BookMeta) book.getItemMeta();

                        bookMeta.setTitle("§bWarp Book");
                        bookMeta.setAuthor(player.getName());

                        ArrayList<String> pages = new ArrayList<String>();

                        Location playerLocation = player.getLocation();

                        // Create the cover page
                        pages.add(String.format(
                                "§k§l12345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912\n\n§r§lWorld\n§r%s\n\n§r§lCoordinates\n§r%d, %d, %d",
                                playerLocation.getWorld().getName(), Math.round(playerLocation.getX()),
                                Math.round(playerLocation.getY()), Math.round(playerLocation.getZ())));

                        // Store the loation information as a JSON string on page 2
                        JsonObject jsonBookData = new JsonObject();
                        jsonBookData.addProperty("type", "written");
                        jsonBookData.addProperty("world", playerLocation.getWorld().getName());
                        jsonBookData.addProperty("x", playerLocation.getX());
                        jsonBookData.addProperty("y", playerLocation.getY());
                        jsonBookData.addProperty("z", playerLocation.getZ());
                        jsonBookData.addProperty("yaw", playerLocation.getYaw());
                        jsonBookData.addProperty("pitch", playerLocation.getPitch());
                        pages.add(jsonBookData.toString());

                        bookMeta.setPages(pages);

                        // The lore apppears on the item's tooltip in your inventory
                        ArrayList<String> lore = new ArrayList<String>();
                        lore.add("");
                        lore.add("§7A magical book with the power");
                        lore.add("§7to teleport whoever opens it");
                        lore.add("§7Sneak + Right Click to use");
                        lore.add("");
                        lore.add(String.format("§7§lWorld §r§7%s", playerLocation.getWorld().getName()));
                        lore.add(String.format("§7§lCoordinates §r§7%d, %d, %d", Math.round(playerLocation.getX()),
                                Math.round(playerLocation.getY()), Math.round(playerLocation.getZ())));
                        bookMeta.setLore(lore);

                        book.setItemMeta(bookMeta);

                        player.getInventory().removeItem(player.getInventory().getItemInMainHand());
                        player.getInventory().addItem(book);

                    } else {

                        player.chat("§cYou do not have permission to write a Warp Book");

                    }

                }

            } catch (Exception e) {

            }

        }

    }

}
