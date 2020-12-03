package dev.vchen04.warpbooks.commands;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class CreateWarpBook implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Location playerLocation = player.getLocation();

            // Create the book item that will become the warp book
            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();

            bookMeta.setTitle("§bWarp Book");

            bookMeta.setAuthor(player.getName());

            ArrayList<String> pages = new ArrayList<String>();

            pages.add(String.format(
                    "§k§l12345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912\n\n§r§lWorld\n§r%s\n\n§r§lCoordinates\n§r%d, %d, %d",
                    playerLocation.getWorld().getName(), Math.round(playerLocation.getX()),
                    Math.round(playerLocation.getY()), Math.round(playerLocation.getZ())));

            // Store the loation information as a JSON string on page 2
            JsonObject jsonPlayerLocation = new JsonObject();
            jsonPlayerLocation.addProperty("world", playerLocation.getWorld().getName());
            jsonPlayerLocation.addProperty("x", playerLocation.getX());
            jsonPlayerLocation.addProperty("y", playerLocation.getY());
            jsonPlayerLocation.addProperty("z", playerLocation.getZ());
            jsonPlayerLocation.addProperty("yaw", playerLocation.getYaw());
            jsonPlayerLocation.addProperty("pitch", playerLocation.getPitch());

            pages.add(jsonPlayerLocation.toString());

            bookMeta.setPages(pages);

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

            player.getInventory().addItem(book);

            return true;

        }

        return false;

    }

}
