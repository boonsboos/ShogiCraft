package xyz.mrsherobrine.ShogiCraft.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;
import xyz.mrsherobrine.ShogiCraft.shogi.pieces.King;
import xyz.mrsherobrine.ShogiCraft.shogi.pieces.Lance;
import xyz.mrsherobrine.ShogiCraft.shogi.pieces.Pawn;
import xyz.mrsherobrine.ShogiCraft.shogi.pieces.Rook;

import java.util.UUID;

public class ArmorStandCreator {

    public static NamespacedKey ownerKey;

    public ArmorStandCreator(JavaPlugin plugin) {
        ownerKey = new NamespacedKey(plugin, "PieceOwner");
    }

    public Piece createPiece(String type, Tile tile, UUID uuid) {

        Piece piece = null;

        Location location = tile.getLocation().toCenterLocation();
        location.setY(location.getBlockY());
        location.setPitch(0);
        location.setYaw(0);

        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCanTick(false);
        armorStand.setVisible(false);
        armorStand.setMarker(true);
        armorStand.getPersistentDataContainer().set(ownerKey, PersistentDataType.STRING, uuid.toString());

        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta meta = paper.getItemMeta();

        switch(type) {
            case "P":
                //pawn
                piece = new Pawn(uuid, armorStand);
                meta.setCustomModelData(1);
                break;
            case "L":
                //lance
                piece = new Lance(uuid, armorStand);
                meta.setCustomModelData(2);
                break;
            case "GK":
                //gote king
                piece = new King(uuid, armorStand);
                meta.setCustomModelData(3);
                break;
            case "R":
                //rook
                piece = new Rook(uuid, armorStand);
                meta.setCustomModelData(4);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        paper.setItemMeta(meta);
        armorStand.setItem(EquipmentSlot.HEAD, paper);

        return piece;
    }

    public NamespacedKey getOwnerKey() {
        return ownerKey;
    }

}
