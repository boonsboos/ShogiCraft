package xyz.mrsherobrine.ShogiCraft.shogi;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import xyz.mrsherobrine.ShogiCraft.listeners.Listeners;
import xyz.mrsherobrine.ShogiCraft.utils.ArmorStandCreator;
import xyz.mrsherobrine.ShogiCraft.utils.LocationChecker;

import java.util.Map;
import java.util.UUID;

public class Game {

    private ArmorStandCreator creator;
    private LocationChecker checker;

    public Game() {
        this.checker = new LocationChecker();
        this.creator = new ArmorStandCreator();
    }

    public void move(Player player, boolean sneaking) {

        Map<String, Tile> tiles = Listeners.clickedTileList;
        Tile from = tiles.get(player.getUniqueId()+"1");
        Tile to = tiles.get(player.getUniqueId()+"2");

        Location toLocation = to.getLocation().toCenterLocation();
        toLocation.setY(to.getLocation().getY());
        toLocation.setYaw(getRoundedAngle((int) from.getLocation().getYaw()));

        if (sneaking) {
                if (from.getPiece() != null && from.getPiece().canMove(from, to, player.getUniqueId()) && !from.getPiece().getType().toString().matches("(K|G)")) {

                    //check if piece belongs to player who's moving
                    if (to.getPiece() != null &&  to.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING))) {
                        player.sendMessage(Component.text("You can't take your own pieces!", NamedTextColor.RED));
                        return;
                    }

                    if (to.getPiece() != null) {
                        capture(to.getPiece().getType(), player.getUniqueId());
                        to.getPiece().getEntity().remove();
                    }

                    to.setPiece(from.getPiece());
                    from.getPiece().getEntity().teleportAsync(toLocation);

                    ItemStack item = from.getPiece().getEntity().getItem(EquipmentSlot.HEAD);
                    ItemMeta meta = item.getItemMeta();
                    meta.setCustomModelData(getPromotedTextureFromType(from.getPiece().getType()));
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

                if (to.getPiece() != null) {
                    capture(to.getPiece().getType(), player.getUniqueId());
                    to.getPiece().getEntity().remove();
                }

                to.setPiece(from.getPiece());
                from.getPiece().getEntity().teleportAsync(toLocation);
                from.setPiece(null);
            } else {
                player.sendMessage(Component.text("Bad move!", NamedTextColor.RED));
            }
        }
        Listeners.clickedTileList.remove(player.getUniqueId()+"1");
        Listeners.clickedTileList.remove(player.getUniqueId()+"2");
    }

    public int getPromotedTextureFromType(PieceType type) {
        //this is for the promoted textures
        return switch (type) {
            case P -> 5;
            case R -> 6;
            case L -> 7;
            case N -> 11;
            case B -> 13;
            case S -> 15;
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

    public void capture(PieceType type, UUID uuid) {

        Player p = Bukkit.getPlayer(uuid);

        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(getTextureFromType(type));
        item.setItemMeta(meta);

        if (p.getInventory().firstEmpty()== -1) {
            Location pLoc = p.getLocation();
            World w = p.getWorld();
            w.dropItemNaturally(pLoc, item);
        } else {
            p.getInventory().addItem(item);
        }

    }

    public void drop(Tile destination, int customModelData, UUID uuid) {

        //TODO get which side which player is on
        destination.setPiece(creator.createPiece(getTypeFromTexture(customModelData), destination, uuid, 0));

    }

    public String getTypeFromTexture(int customModelData) {
        return switch(customModelData) {
            case 1 -> "P";
            case 2 -> "L";
            case 4 -> "R";
            case 8 -> "S";
            case 9 -> "G";
            case 10 -> "N";
            case 12 -> "B";
            default -> throw new IllegalStateException("Unexpected value: " + customModelData);
        };
    }

    public int getTextureFromType(PieceType type) {
        return switch (type) {
            case P -> 1;
            case R -> 4;
            case L -> 2;
            case N -> 10;
            case B -> 12;
            case S -> 8;
            case G -> 9;
            default -> throw new IllegalStateException("Unexpected value: "+ type);
        };
    }

    public void setupGame(Tile[][] board) {



    }

}