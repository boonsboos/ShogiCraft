package xyz.mrsherobrine.ShogiCraft;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.mrsherobrine.ShogiCraft.commands.CommandHandler;
import xyz.mrsherobrine.ShogiCraft.listeners.Test;

public class ShogiCraft extends JavaPlugin {

    @Override
    public void onEnable() {

        //getServer().getPluginManager().registerEvents(new Test(this) ,this);
        getCommand("shogi").setExecutor(new CommandHandler(this));
    }

}
