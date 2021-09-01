package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.entity.ArmorStand;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;

import java.util.UUID;

public class King extends Piece  {

    public King(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        setType("K");
    }


}
