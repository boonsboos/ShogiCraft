package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;

import java.util.UUID;

public class Pawn extends Piece {

    public Pawn(UUID owner) {
        super(owner);
        setType("P");
    }

    public boolean canMove(Location oldLoc, Location newLoc, BlockFace facing) {

        return true;
    }
}
