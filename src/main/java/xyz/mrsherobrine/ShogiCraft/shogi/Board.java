package xyz.mrsherobrine.ShogiCraft.shogi;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.UUID;

public class Board {

    public UUID owner;
    public Square[][] board = new Square[9][9];

    public Square[][] createNewBoard(JavaPlugin plugin, int[] coords, UUID owner, Location location) {

        plugin.getLogger().info("Created new Board with parameters: "+ Arrays.toString(coords)+", Owned by: "+Bukkit.getPlayer(owner).getName());

        this.owner = owner;

        location.setX((double) (int) location.getX()-4.0);
        location.setZ((double)(int)location.getZ()-4.0);
        Location corner = new Location(location.getWorld(), location.getBlockX(),location.getBlockY(), location.getBlockZ());

        int blockCounter = 0;
        int blockX = 0;
        int blockY = 0;

        //same code as in utils.LocationChecker, but modified to work for the board instead

        while (blockCounter <81) {
            if (location.getZ()-corner.getZ() < 8 && blockY<8) {
                location.setZ(location.getZ() + 1);
                board[blockY][blockX] = new Square(location);
                blockY++;
                blockCounter++;
            } else {
                location.setX(location.getX()+1);
                location.setZ(corner.getZ());
                board[blockY][blockX] = new Square(location);
                blockX++;
                blockY=0;
                blockCounter++;
            }
        }
        //TODO: insert stuff into db
        return board;
    }

    public Square getSquare(int row, int column){
        return board[row][column];
    }

    public UUID getOwner() {
        return owner;
    }

}
