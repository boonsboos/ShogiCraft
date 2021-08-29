package xyz.mrsherobrine.ShogiCraft.shogi;

import org.bukkit.Location;

public class Square {

    private Location location;
    private Piece piece;

    public Square(Location location) {
        this.location = location;
    }

    public Piece getPiece() {
        return piece;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}