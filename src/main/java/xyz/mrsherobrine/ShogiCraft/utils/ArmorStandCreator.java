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
import org.bukkit.util.EulerAngle;
import xyz.mrsherobrine.ShogiCraft.ShogiCraft;
import xyz.mrsherobrine.ShogiCraft.shogi.Piece;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;
import xyz.mrsherobrine.ShogiCraft.shogi.pieces.*;

import java.util.UUID;

public class ArmorStandCreator {

    public static NamespacedKey ownerKey;

    public ArmorStandCreator() {
        ownerKey = new NamespacedKey(ShogiCraft.getPlugin(ShogiCraft.class), "PieceOwner");
    }

    public Piece createPiece(String type, Tile tile, UUID uuid, int yaw) {

        Piece piece = null;

        Location location = tile.getLocation().toCenterLocation();
        location.setY(location.getBlockY());
        location.setPitch(0);
        location.setYaw(0);

        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCanTick(false);
        armorStand.setVisible(false);
        armorStand.setMarker(true);
        armorStand.setHeadPose(new EulerAngle(0,Math.toRadians(yaw),0));
        armorStand.getPersistentDataContainer().set(ownerKey, PersistentDataType.STRING, uuid.toString());

        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta meta = paper.getItemMeta();

        switch (type) {
            case "P" -> {
                //pawn
                piece = new Pawn(uuid, armorStand);
                meta.setCustomModelData(1);
            }
            case "L" -> {
                //lance
                piece = new Lance(uuid, armorStand);
                meta.setCustomModelData(2);
            }
            case "GK" -> {
                //gote king
                piece = new King(uuid, armorStand);
                meta.setCustomModelData(3);
            }
            case "R" -> {
                //rook
                piece = new Rook(uuid, armorStand);
                meta.setCustomModelData(4);
            }
            case "S" -> {
                //silver
                piece = new Silver(uuid, armorStand);
                meta.setCustomModelData(8);
            }
            case "G" -> {
                //gold
                piece = new Gold(uuid, armorStand);
                meta.setCustomModelData(9);
            }
            case "N" -> {
                //knight
                piece = new Knight(uuid, armorStand);
                meta.setCustomModelData(10);
            }
            case "B" -> {
                //bishop
                piece = new Bishop(uuid, armorStand);
                meta.setCustomModelData(12);
            }
            case "SK" -> {
                piece = new King(uuid, armorStand);
                meta.setCustomModelData(15);
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }

        paper.setItemMeta(meta);
        armorStand.setItem(EquipmentSlot.HEAD, paper);

        return piece;
    }

}