package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;

import java.util.UUID;

public class Pawn extends Piece {

    public Pawn(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        setType("P");
    }

    public boolean canMove(Tile from, Tile to, BlockFace facing) {
        //can only move one tile forward, unless promoted
        return true;
    }
}
