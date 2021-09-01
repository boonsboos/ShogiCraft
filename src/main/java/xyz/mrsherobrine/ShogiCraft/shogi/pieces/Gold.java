package xyz.mrsherobrine.ShogiCraft.shogi.pieces;

import org.bukkit.entity.ArmorStand;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;

import java.util.UUID;

public class Gold extends Piece {

    public Gold(UUID owner, ArmorStand armorStand) {
        super(owner, armorStand);
        setType("G");
    }

    @Override
    public boolean canMove(Tile from, Tile to, UUID uuid) {

        //can be either toX -1 or +1 (forward/backward)
        //can be either toZ -1 or +1 (left/right)
        //can be toX -1 and toZ -1 or +1 (diagonal forward)

        //can NOT be toX +1 and toZ -1 or +1 (diagonal backward

        //BUT this really depends on the facing.



        return true;
    }

}
