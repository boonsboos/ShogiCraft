package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.entity.ArmorStand;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;

import java.util.UUID;

public class Bishop extends Piece {

    public Bishop(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        setType("B");
    }

    @Override
    public boolean canMove(Tile from, Tile to, UUID uuid) {



        return false;
    }
}