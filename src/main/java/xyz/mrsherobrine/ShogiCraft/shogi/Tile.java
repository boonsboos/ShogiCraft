package xyz.mrsherobrine.ShogiCraft.shogi;

import org.bukkit.Location;

public class Tile {

    private final Location location;
    private Piece piece;

    public Tile(Location location) {
        this.location = location;
    }

    public Piece getPiece() {
        return piece;
    }

    public Location getLocation() {
        return location;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
