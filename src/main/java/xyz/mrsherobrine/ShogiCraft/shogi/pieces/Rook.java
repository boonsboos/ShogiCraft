package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.entity.ArmorStand;
import org.bukkit.persistence.PersistentDataType;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;
import xyz.mrsherobrine.ShogiCraft.shogi.PieceType;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;
import xyz.mrsherobrine.ShogiCraft.utils.ArmorStandCreator;

import java.util.UUID;

public class Rook extends Piece {


    public Rook(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        setType(PieceType.R);
    }

    @Override
    public boolean canMove(Tile from, Tile to, UUID uuid) {

        //needs to be on the same X or same Z level

        if (from.getPiece().getEntity().getHeadPose().getY() == 0 && from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(uuid.toString())) {

            if (!isPromoted()) {

                return from.getLocation().getBlockZ() == to.getLocation().getBlockZ() || from.getLocation().getBlockX() == to.getLocation().getBlockX();

            } else {

                if (from.getLocation().getBlockZ() == to.getLocation().getBlockZ() || from.getLocation().getBlockX() == to.getLocation().getBlockX()) {
                    return true;
                //this does diagonal
                } else if (from.getLocation().getBlockX() - to.getLocation().getBlockX() == 1 && from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == 1) {
                    return true;
                } else if (from.getLocation().getBlockX() - to.getLocation().getBlockX() == -1 && from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == -1) {
                    return true;
                } else if (from.getLocation().getBlockZ()- to.getLocation().getBlockZ() ==  -1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == 1) {
                    return true;
                } else return from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == 1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == -1;

            }

        } else if (from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(uuid.toString())) {

            if (!isPromoted()) {

                return from.getLocation().getBlockZ() == to.getLocation().getBlockZ() || from.getLocation().getBlockX() == to.getLocation().getBlockX();

            } else {

                if (from.getLocation().getBlockZ() == to.getLocation().getBlockZ() || from.getLocation().getBlockX() == to.getLocation().getBlockX()) {
                    return true;
                    //this does diagonal
                } else if (from.getLocation().getBlockX() - to.getLocation().getBlockX() == 1 && from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == 1) {
                    return true;
                } else if (from.getLocation().getBlockX() - to.getLocation().getBlockX() == -1 && from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == -1) {
                    return true;
                } else if (from.getLocation().getBlockZ()- to.getLocation().getBlockZ() ==  -1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == 1) {
                    return true;
                } else return from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == 1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == -1;

            }

        }

        return false;

    }

}