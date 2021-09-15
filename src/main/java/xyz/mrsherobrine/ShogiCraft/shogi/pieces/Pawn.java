package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.entity.ArmorStand;
import org.bukkit.persistence.PersistentDataType;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;
import xyz.mrsherobrine.ShogiCraft.shogi.enums.PieceType;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;
import xyz.mrsherobrine.ShogiCraft.utils.ArmorStandCreator;

import java.util.UUID;

public class Pawn extends Piece {

    public Pawn(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        setType(PieceType.P);
    }

    @Override
    public boolean canMove(Tile from, Tile to, UUID uuid) {

        if (from.getPiece().getEntity().getHeadPose().getY() == 0 && from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(uuid.toString()) && from.getLocation() != to.getLocation()) {


            if (!isPromoted()) {
                return from.getLocation().getBlockZ() == to.getLocation().getBlockZ() - 1 && from.getLocation().getBlockX() == to.getLocation().getBlockX();

            } else {

                //yeah, it's copypasted from the Gold class, what you gonna do?
                if (from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == -1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == -1) {
                    return true;
                } else if (from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == -1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == 1) {
                    return true;
                    //right
                } else if (from.getLocation().getBlockX() - to.getLocation().getBlockX() == 1 && from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == 0) {
                    return true;
                    //left
                } else if (from.getLocation().getBlockX() - to.getLocation().getBlockX() == -1 && from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == 0) {
                    return true;
                    //forward
                } else if (from.getLocation().getBlockZ() == to.getLocation().getBlockZ() - 1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == 0) {
                    return true;
                    //backward
                } else return from.getLocation().getBlockZ() == to.getLocation().getBlockZ() + 1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == 0;

            }

        } else if (from.getPiece().getEntity().getHeadPose().getY() == Math.PI && from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(uuid.toString()) && from.getLocation() != to.getLocation()) {

            if (!isPromoted()) {

                return from.getLocation().getBlockZ() == to.getLocation().getBlockZ() + 1 && from.getLocation().getBlockX() == to.getLocation().getBlockX();

            } else {

                //forward diagonal
                if (from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == 1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == -1) {
                    return true;
                } else if (from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == 1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == 1) {
                    return true;
                    //right
                } else if (from.getLocation().getBlockX() - to.getLocation().getBlockX() == 1 && from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == 0) {
                    return true;
                    //left
                } else if (from.getLocation().getBlockX() - to.getLocation().getBlockX() == -1 && from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == 0) {
                    return true;
                    //forward
                } else if (from.getLocation().getBlockZ() == to.getLocation().getBlockZ() - 1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == 0) {
                    return true;
                    //backward
                } else
                    return from.getLocation().getBlockZ() == to.getLocation().getBlockZ() + 1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == 0;

            }

        }

        return  false;

        //only moves one tile, promotion not implemented yet

    }
}