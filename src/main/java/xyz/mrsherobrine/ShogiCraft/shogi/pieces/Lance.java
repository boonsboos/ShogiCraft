package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.entity.ArmorStand;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;

import java.util.UUID;

public class Lance extends Piece {


    public Lance(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        setType("L");
    }

    public boolean canMove() {
        //can only move in same column
        return  true;
    }

}
