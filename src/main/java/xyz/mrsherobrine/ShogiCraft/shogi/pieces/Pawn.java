package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;

import java.util.UUID;

public class Pawn extends Piece {

    public Pawn(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        setType("P");
    }

    public boolean canMove(Location oldLoc, Location newLoc, BlockFace facing) {

        return true;
    }
}
