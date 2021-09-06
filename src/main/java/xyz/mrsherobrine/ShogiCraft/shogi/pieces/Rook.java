package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.entity.ArmorStand;
import org.bukkit.persistence.PersistentDataType;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;
import xyz.mrsherobrine.ShogiCraft.utils.ArmorStandCreator;

import java.util.UUID;

public class Rook extends Piece {


    public Rook(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        setType("R");
    }

    @Override
    public boolean canMove(Tile from, Tile to, UUID uuid) {

        //needs to be on the same X or same Z level

        if (from.getPiece().getEntity().getHeadPose().getY() == 0 && from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(uuid.toString())) {
            return (from.getLocation().getBlockZ() == to.getLocation().getBlockZ() || from.getLocation().getBlockX() == to.getLocation().getBlockX())  && !isPromoted();
        }

        return false;

    }

}
