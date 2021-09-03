package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.persistence.PersistentDataType;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;
import xyz.mrsherobrine.ShogiCraft.utils.ArmorStandCreator;

import java.util.UUID;

public class King extends Piece  {

    public King(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        this.setType("K");
    }

    @Override
    public boolean canMove(Tile from, Tile to, UUID uuid) {

        if (from.getLocation() != to.getLocation() && from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(uuid.toString())) {
            return Math.abs(from.getLocation().getBlockX() - to.getLocation().getBlockX()) <=1 && (Math.abs(from.getLocation().getBlockZ() - to.getLocation().getBlockZ()) <= 1) && !isPromoted();
        }
        return  false;
    }

}