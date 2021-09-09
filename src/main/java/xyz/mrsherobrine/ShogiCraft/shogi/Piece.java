package xyz.mrsherobrine.ShogiCraft.shogi;

import org.bukkit.entity.ArmorStand;

import java.util.UUID;

public class Piece {

    private PieceType type;
    private UUID owner;
    private ArmorStand armorStand;
    private boolean promotion = false;

    public Piece(UUID owner, ArmorStand armorStand) {
        this.armorStand = armorStand;
        this.owner = owner;
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType newType) {
        this.type = newType;
    }

    public UUID getOwner() {
        return owner;
    }

    public boolean isPromoted() {
        return promotion;
    }

    public void setPromoted(boolean promote) {
        if (promote && !this.promotion) {
            this.promotion = promote;
        }
    }

    public ArmorStand getEntity() {
        return armorStand;
    }

    public void setEntity(ArmorStand as) {
        this.armorStand = as;
    }

    public boolean canMove(Tile from, Tile to, UUID uuid) {
        return true;
    }

}