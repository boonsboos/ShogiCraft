package xyz.mrsherobrine.ShogiCraft.shogi;

import org.bukkit.Material;

public class Game {

    public void setupGame(Square[][] board) {
        //lance
        board[0][0].getLocation().getBlock().setType(Material.DARK_OAK_WOOD);
        board[0][8].getLocation().getBlock().setType(Material.DARK_OAK_WOOD);
        //knight
        board[0][1].getLocation().getBlock().setType(Material.HAY_BLOCK);
        board[0][7].getLocation().getBlock().setType(Material.HAY_BLOCK);
        //silver
        board[0][2].getLocation().getBlock().setType(Material.IRON_BLOCK);
        board[0][6].getLocation().getBlock().setType(Material.IRON_BLOCK);
        //gold
        board[0][3].getLocation().getBlock().setType(Material.GOLD_BLOCK);
        board[0][5].getLocation().getBlock().setType(Material.GOLD_BLOCK);
        //king
        board[0][4].getLocation().getBlock().setType(Material.DIAMOND_BLOCK);
        //rook
        board[1][1].getLocation().getBlock().setType(Material.STONE);
        //bishop
        board[1][7].getLocation().getBlock().setType(Material.QUARTZ_BLOCK);
        //pawn
        board[2][0].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[2][1].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[2][2].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[2][3].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[2][4].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[2][5].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[2][6].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[2][7].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[2][8].getLocation().getBlock().setType(Material.COBBLESTONE);

    }


}
