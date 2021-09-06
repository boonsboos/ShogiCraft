package xyz.mrsherobrine.ShogiCraft.shogi;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import xyz.mrsherobrine.ShogiCraft.listeners.Test;
import xyz.mrsherobrine.ShogiCraft.utils.ArmorStandCreator;

import java.util.Map;

public class Game {

    public void move(Player player, boolean sneaking) {

        Map<String, Tile> tiles = Test.clickedTileList;
        Tile from = tiles.get(player.getUniqueId()+"1");
        Tile to = tiles.get(player.getUniqueId()+"2");

        Location toLocation = to.getLocation().toCenterLocation();
        toLocation.setY(to.getLocation().getY());
        toLocation.setYaw(getRoundedAngle((int) from.getLocation().getYaw()));

        if (sneaking) {
                if (from.getPiece() != null && from.getPiece().canMove(from, to, player.getUniqueId()) && !from.getPiece().getType().matches("(K|G)")) {

                    //check if piece belongs to player who's moving
                    if (to.getPiece() != null &&  to.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING))) {
                        player.sendMessage(Component.text("You can't take your own pieces!", NamedTextColor.RED));
                        return;
                    }

                    //run capturing logic here
                    if (to.getPiece() != null) {
                        to.getPiece().getEntity().remove();
                    }

                    to.setPiece(from.getPiece());
                    from.getPiece().getEntity().teleportAsync(toLocation);

                    ItemStack item = from.getPiece().getEntity().getItem(EquipmentSlot.HEAD);
                    ItemMeta meta = item.getItemMeta();
                    meta.setCustomModelData(getCorrectTextureFromType(from.getPiece().getType()));
                    item.setItemMeta(meta);
                    from.getPiece().getEntity().setItem(EquipmentSlot.HEAD, item);
                    //TODO this can all be done in a different method

                    from.getPiece().setPromoted(true);
                    from.setPiece(null);
                } else {
                    player.sendMessage(Component.text("Bad move or can't promote!", NamedTextColor.RED));
                }
        } else {
            if (from.getPiece() != null && from.getPiece().canMove(from, to, player.getUniqueId())) {

                if (to.getPiece() != null &&  to.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING))) {
                    player.sendMessage(Component.text("You can't take your own pieces!", NamedTextColor.RED));
                    return;
                }

                //run capturing logic here
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
        //this is for the promoted textures
        return switch (type) {
            case "P" -> 5;
            case "R" -> 6;
            case "L" -> 7;
            case "N" -> 11;
            case "B" -> 13;
            default -> 3;
        };
    }

    public int getRoundedAngle(int angle) {
        if (angle >=180) {
            return 180;
        } else {
            return 0;
        }
    }

}