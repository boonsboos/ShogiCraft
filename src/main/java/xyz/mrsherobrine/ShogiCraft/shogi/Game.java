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
        board[1][1].getLocation().getBlock().setType(Material.BRICKS);
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


        //same but gote
        board[8][0].getLocation().getBlock().setType(Material.DARK_OAK_WOOD);
        board[8][8].getLocation().getBlock().setType(Material.DARK_OAK_WOOD);
        //knight
        board[8][1].getLocation().getBlock().setType(Material.HAY_BLOCK);
        board[8][7].getLocation().getBlock().setType(Material.HAY_BLOCK);
        //silver
        board[8][2].getLocation().getBlock().setType(Material.IRON_BLOCK);
        board[8][6].getLocation().getBlock().setType(Material.IRON_BLOCK);
        //gold
        board[8][3].getLocation().getBlock().setType(Material.GOLD_BLOCK);
        board[8][5].getLocation().getBlock().setType(Material.GOLD_BLOCK);
        //king
        board[8][4].getLocation().getBlock().setType(Material.REDSTONE_BLOCK);
        //rook
        board[7][7].getLocation().getBlock().setType(Material.BRICKS);
        //bishop
        board[7][1].getLocation().getBlock().setType(Material.QUARTZ_BLOCK);
        //pawn
        board[6][0].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[6][1].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[6][2].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[6][3].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[6][4].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[6][5].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[6][6].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[6][7].getLocation().getBlock().setType(Material.COBBLESTONE);
        board[6][8].getLocation().getBlock().setType(Material.COBBLESTONE);

    }


}
