package dev.vchen04.warpbooks.commands;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class UseWarpBook implements CommandExecutor {

    final Gson gson = new Gson();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            try {

                ItemStack book = player.getInventory().getItemInMainHand();

                BookMeta bookMeta = (BookMeta) book.getItemMeta();

                JsonObject bookPositionData = gson.fromJson(bookMeta.getPage(2), JsonObject.class);

                player.teleport(new Location(Bukkit.getWorld(bookPositionData.get("world").getAsString()),
                        bookPositionData.get("x").getAsInt(), bookPositionData.get("y").getAsInt(),
                        bookPositionData.get("z").getAsInt()));

                return true;

            }

            catch (Exception e) {

                return false;

            }

        }

        return false;

    }

}
