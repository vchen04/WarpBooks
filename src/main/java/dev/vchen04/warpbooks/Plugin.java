package dev.vchen04.warpbooks;

import org.bukkit.plugin.java.JavaPlugin;

import dev.vchen04.warpbooks.commands.CreateWarpBook;
import dev.vchen04.warpbooks.commands.UseWarpBook;

public class Plugin extends JavaPlugin {

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        this.getCommand("createwarpbook").setExecutor(new CreateWarpBook());
        this.getCommand("usewarpbook").setExecutor(new UseWarpBook());
    }

    @Override
    public void onDisable() {
        
    }

}
