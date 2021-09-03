package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.entity.ArmorStand;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;

import java.util.UUID;

public class Knight extends Piece {

    public Knight(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        setType("N");
    }
}