package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.persistence.PersistentDataType;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;
import xyz.mrsherobrine.ShogiCraft.utils.ArmorStandCreator;

import java.util.UUID;

public class Pawn extends Piece {

    public Pawn(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        setType("P");
    }

    @Override
    public boolean canMove(Tile from, Tile to, UUID uuid) {

        if (from.getPiece().getEntity().getFacing() == BlockFace.SOUTH && from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(uuid.toString()) && from.getLocation() != to.getLocation()) {
            return from.getLocation().getBlockZ() == to.getLocation().getBlockZ() - 1 && from.getLocation().getBlockX() == to.getLocation().getBlockX() && !isPromoted();
        } else if (from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(uuid.toString()) && from.getLocation() != to.getLocation()) {
            return from.getLocation().getBlockZ() == to.getLocation().getBlockZ() + 1 && from.getLocation().getBlockX() == to.getLocation().getBlockX() && !isPromoted();
        } else {
            return  false;
        }

        //only moves one tile, promotion not implemented yet

    }
}