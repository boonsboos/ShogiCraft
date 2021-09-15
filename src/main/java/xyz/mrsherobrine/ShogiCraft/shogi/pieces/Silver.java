package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.entity.ArmorStand;
import org.bukkit.persistence.PersistentDataType;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;
import xyz.mrsherobrine.ShogiCraft.shogi.enums.PieceType;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;
import xyz.mrsherobrine.ShogiCraft.utils.ArmorStandCreator;

import java.util.UUID;

public class Silver extends Piece {

    public Silver(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        setType(PieceType.S);
    }

    @Override
    public boolean canMove(Tile from, Tile to, UUID uuid) {

        //i hate that it has to be this disgusting

        if (from.getPiece().getEntity().getHeadPose().getY() == 0 && from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(uuid.toString()) && from.getLocation() != to.getLocation()) {

            if (!isPromoted()) {

                //backward right
                if (from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == -1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == -1) {
                    return true;
                //backward left
                } else if (from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == 1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == -1) {
                    return true;
                //forward left
                } else if (from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == 1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == 1) {
                    return true;
                //forward right
                } else if (from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == -1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == 1) {
                    return true;
                //normal forward
                } else return from.getLocation().getBlockZ() == to.getLocation().getBlockZ() - 1;

            } else {

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

                if (from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == -1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == -1) {
                    return true;
                } else if (from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == 1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == -1) {
                    return true;
                } else if (from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == 1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == 1) {
                    return true;
                } else if (from.getLocation().getBlockZ() - to.getLocation().getBlockZ() == -1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == 1) {
                    return true;
                } else return from.getLocation().getBlockZ() == to.getLocation().getBlockZ() +1;

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
                } else return from.getLocation().getBlockZ() == to.getLocation().getBlockZ() + 1 && from.getLocation().getBlockX() - to.getLocation().getBlockX() == 0;

            }

        }

        return false;

    }

}