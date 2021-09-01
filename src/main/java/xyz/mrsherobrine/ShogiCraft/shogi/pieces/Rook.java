package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.entity.ArmorStand;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;

import java.util.UUID;

public class Rook extends Piece {


    public Rook(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        setType("R");
    }

    @Override
    public boolean canMove(Tile from, Tile to, UUID uuid) {

        //needs to be on the same X or same Z level

        return true;
    }

}
