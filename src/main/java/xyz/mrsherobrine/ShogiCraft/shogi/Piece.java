package xyz.mrsherobrine.ShogiCraft.shogi;

import java.util.UUID;

public class Piece {

    private String type;
    private UUID owner;
    private boolean promotion = false;

    public Piece(UUID owner) {
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public void setType(String newType) {
        this.type = newType;
    }

    public UUID getOwner() {
        return owner;
    }

    public boolean isPromoted() {
        return promotion;
    }

    public void setPromoted(boolean promote) {
        promotion = promote;
        if (promote) {
            type = "!" + type;
        } else if (type.contains("!")) {
            type.replace("!", "");
        }
    }

}