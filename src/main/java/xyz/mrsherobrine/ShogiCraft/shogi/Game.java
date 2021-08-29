package xyz.mrsherobrine.ShogiCraft.shogi;

import org.bukkit.Material;

public class Game {

    public void setupGame(Square[][] board) {
        board[0][1].getLocation().getBlock().setType(Material.IRON_ORE);
    }


}
