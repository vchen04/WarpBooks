package dev.vchen04.warpbooks;

import org.bukkit.plugin.java.JavaPlugin;

import dev.vchen04.warpbooks.listeners.UseBlankWarpBook;
import dev.vchen04.warpbooks.listeners.UseWarpBook;
import dev.vchen04.warpbooks.recipes.BlankWarpBook;

public class Plugin extends JavaPlugin {

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        BlankWarpBook.createRecipe(this);

        getServer().getPluginManager().registerEvents(new UseWarpBook(), this);
        getServer().getPluginManager().registerEvents(new UseBlankWarpBook(), this);
    }

    @Override
    public void onDisable() {

    }

}
