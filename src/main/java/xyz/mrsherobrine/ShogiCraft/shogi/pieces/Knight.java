package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.entity.ArmorStand;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;

import java.util.UUID;

public class Knight extends Piece {

    public Knight(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        setType("N");
    }

    @Override
    public boolean canMove(Tile from, Tile to, UUID uuid) {

        //2 forward, 1 to the right/left


        return false;
    }
}