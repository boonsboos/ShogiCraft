package xyz.mrsherobrine.ShogiCraft.shogi;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.mrsherobrine.ShogiCraft.listeners.Test;

import java.util.Map;

public class Game {

    public void move(Player player, boolean sneaking) {

        Map<String, Tile> tiles = Test.clickedTileList;
        Tile from = tiles.get(player.getUniqueId()+"1");
        Tile to = tiles.get(player.getUniqueId()+"2");

        Location toLocation = to.getLocation().toCenterLocation();
        toLocation.setY(to.getLocation().getY());
        toLocation.setYaw(0);
        toLocation.setPitch(0);

        if (sneaking) {
            player.sendMessage(Component.text("Promotion?", NamedTextColor.AQUA));
            if (from.getPiece() != null && from.getPiece().canMove(from, to, player.getUniqueId())) {
                if (to.getPiece() != null) {
                    to.getPiece().getEntity().remove();
                }
                to.setPiece(from.getPiece());
                from.getPiece().getEntity().teleportAsync(toLocation);
                from.getPiece().setPromoted(true);
                //this
                    ItemStack item = from.getPiece().getEntity().getItem(EquipmentSlot.HEAD);
                    ItemMeta meta = item.getItemMeta();
                    meta.setCustomModelData(3);
                    item.setItemMeta(meta);
                    from.getPiece().getEntity().setItem(EquipmentSlot.HEAD, item);
                //TODO can all be done in a different method
                from.setPiece(null);
            } else {
                player.sendMessage(Component.text("Bad move!", NamedTextColor.RED));
            }
        } else {
            if (from.getPiece() != null && from.getPiece().canMove(from, to, player.getUniqueId())) {
                if (to.getPiece() != null) {
                    to.getPiece().getEntity().remove();
                }
                to.setPiece(from.getPiece());
                from.getPiece().getEntity().teleportAsync(toLocation);
                from.setPiece(null);
            } else {
                player.sendMessage(Component.text("Bad move!", NamedTextColor.RED));
            }
        }
        Test.clickedTileList.remove(player.getUniqueId()+"1");
        Test.clickedTileList.remove(player.getUniqueId()+"2");
    }

    public int getCorrectTextureFromType(String type) {

        switch(type) {
            //TODO: add stuff here >:c
        }

        return 0;
    }

}
