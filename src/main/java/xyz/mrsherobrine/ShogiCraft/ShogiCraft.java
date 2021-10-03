package xyz.mrsherobrine.ShogiCraft;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.mrsherobrine.ShogiCraft.commands.CommandHandler;
import xyz.mrsherobrine.ShogiCraft.listeners.Listeners;

public class ShogiCraft extends JavaPlugin {

  @Override
  public void onEnable() {
    
    getServer().getPluginManager().registerEvents(new Listeners() ,this);
    getCommand("shogi").setExecutor(new CommandHandler(this));
    
  }

}
