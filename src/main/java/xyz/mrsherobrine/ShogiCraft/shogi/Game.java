package xyz.mrsherobrine.ShogiCraft.shogi;

import org.bukkit.entity.Player;
import xyz.mrsherobrine.ShogiCraft.listeners.Test;

import java.util.Map;

public class Game {

    public Game() {
    }

    public void move(Player player) {

        Map<String, Tile> tiles = Test.clickedTileList;
        Tile from = tiles.get(player.getUniqueId()+"1");
        Tile to = tiles.get(player.getUniqueId()+"2");

        from.getPiece().getEntity().teleportAsync(to.getLocation().toBlockLocation());
        Test.clickedTileList.clear();
    }

}
