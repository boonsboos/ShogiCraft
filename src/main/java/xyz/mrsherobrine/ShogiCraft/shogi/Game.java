package xyz.mrsherobrine.ShogiCraft.shogi;

import org.bukkit.Location;
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

        Location toLocation = to.getLocation().toCenterLocation();
        toLocation.setY(to.getLocation().getY());
        toLocation.setYaw(0);
        toLocation.setPitch(0);

        if (from.getPiece() != null && from.getPiece().canMove(from, to, player.getUniqueId())) {
            if (to.getPiece() != null) {
                to.getPiece().getEntity().remove();
            }
            to.setPiece(from.getPiece());
            from.getPiece().getEntity().teleport(toLocation);
            from.setPiece(null);
        } else {
            player.sendMessage("Bad move!");
        }
        Test.clickedTileList.remove(player.getUniqueId()+"1");
        Test.clickedTileList.remove(player.getUniqueId()+"2");
    }

}
