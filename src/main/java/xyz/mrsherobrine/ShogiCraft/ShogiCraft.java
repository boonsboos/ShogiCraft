package xyz.mrsherobrine.ShogiCraft;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.mrsherobrine.ShogiCraft.shogi.Board;

public class ShogiCraft extends JavaPlugin {

    @Override
    public void onEnable() {

        new Board(0, 0, 20, 20, Bukkit.getPlayer("Mrs_Herobrine_").getUniqueId()).createNewBoard(this);

    }

}
