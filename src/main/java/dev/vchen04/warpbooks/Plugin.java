package dev.vchen04.warpbooks;

import org.bukkit.plugin.java.JavaPlugin;

import dev.vchen04.warpbooks.listeners.UseBlankWarpBookListener;
import dev.vchen04.warpbooks.listeners.UseLocationWarpBookListener;
import dev.vchen04.warpbooks.recipes.BlankWarpBookRecipe;

public class Plugin extends JavaPlugin {

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        BlankWarpBookRecipe.createRecipe(this);

        getServer().getPluginManager().registerEvents(new UseLocationWarpBookListener(this), this);
        getServer().getPluginManager().registerEvents(new UseBlankWarpBookListener(this), this);
    }

    @Override
    public void onDisable() {

    }

}
