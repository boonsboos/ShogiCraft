package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.persistence.PersistentDataType;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;
import xyz.mrsherobrine.ShogiCraft.utils.ArmorStandCreator;

import java.util.UUID;

public class Lance extends Piece {


    public Lance(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        setType("L");
    }

    @Override
    public boolean canMove(Tile from, Tile to, UUID uuid) {
        //can only move in same column

        if (from.getPiece().getEntity().getHeadPose().getY() == 0 && from.getLocation() != to.getLocation() && from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(uuid.toString())) {
            return from.getLocation().getBlockX() == to.getLocation().getBlockX() && from.getLocation().getBlockZ() < to.getLocation().getBlockZ() && !isPromoted();
        } else if (from.getLocation() != to.getLocation() && from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(uuid.toString())) {
            return from.getLocation().getBlockX() == to.getLocation().getBlockX() && from.getLocation().getBlockZ() > to.getLocation().getBlockZ() && !isPromoted();
        }
        return  false;
    }

}
