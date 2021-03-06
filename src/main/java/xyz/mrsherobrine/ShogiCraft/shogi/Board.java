package xyz.mrsherobrine.ShogiCraft.shogi;

import org.bukkit.Location;

import java.util.UUID;

public class Board {

    private UUID owner;
    private Tile[][] board = new Tile[9][9];

    public Board(UUID owner, Location location) {
        this.owner = owner;
        this.board = createNewBoard(location);
    }

    public Tile[][] createNewBoard(Location location) {

        //shift location to be a corner
        location.setX((double) (int) location.getX()-4.0);
        location.setZ((double)(int)location.getZ()-4.0);
        location.setY((double)(int)location.getY()-1.0);
        Location corner = new Location(location.getWorld(), location.getBlockX(),location.getY(), location.getBlockZ());

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                board[y][x] = new Tile(location.clone());
                location.setZ(location.getZ()+1);
            }
            //shift to the next row
            location.setX(location.getX()+1.0);
            //reset Z so it doesn't continue past 9
            location.setZ(corner.getZ());
        }
        return board;
    }

    public Tile getSquare(int row, int column){
        return board[row][column];
    }

    public Tile[][] getBoard() {
        return board;
    }

    public UUID getOwner() {
        return owner;
    }

}