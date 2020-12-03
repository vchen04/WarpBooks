package dev.vchen04.warpbooks;

import org.bukkit.plugin.java.JavaPlugin;

import dev.vchen04.warpbooks.commands.CreateWarpBook;
import dev.vchen04.warpbooks.listeners.UseWarpBookListener;

public class Plugin extends JavaPlugin {

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        this.getCommand("createwarpbook").setExecutor(new CreateWarpBook());

        getServer().getPluginManager().registerEvents(new UseWarpBookListener(), this);
    }

    @Override
    public void onDisable() {
        
    }

}
