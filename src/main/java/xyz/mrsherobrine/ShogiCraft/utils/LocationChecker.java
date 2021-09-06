package xyz.mrsherobrine.ShogiCraft.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class LocationChecker {

    private Logger logger;

    private Location corner;
    private Location location;

    private List<Material> woods = Arrays.asList(

            Material.ACACIA_PLANKS,
            Material.BIRCH_PLANKS,
            Material.OAK_PLANKS,
            Material.JUNGLE_PLANKS,
            Material.DARK_OAK_PLANKS,
            Material.CRIMSON_PLANKS,
            Material.WARPED_PLANKS,
            Material.SPRUCE_PLANKS,
            Material.ACACIA_WOOD,
            Material.BIRCH_WOOD,
            Material.DARK_OAK_WOOD,
            Material.JUNGLE_WOOD,
            Material.OAK_WOOD,
            Material.SPRUCE_WOOD,
            Material.STRIPPED_ACACIA_WOOD,
            Material.STRIPPED_ACACIA_LOG,
            Material.STRIPPED_BIRCH_LOG,
            Material.STRIPPED_BIRCH_WOOD,
            Material.STRIPPED_BIRCH_WOOD,
            Material.STRIPPED_CRIMSON_HYPHAE,
            Material.STRIPPED_CRIMSON_STEM,
            Material.STRIPPED_DARK_OAK_LOG,
            Material.STRIPPED_DARK_OAK_WOOD,
            Material.STRIPPED_JUNGLE_LOG,
            Material.STRIPPED_JUNGLE_WOOD,
            Material.STRIPPED_OAK_LOG,
            Material.STRIPPED_OAK_WOOD,
            Material.STRIPPED_OAK_WOOD,
            Material.STRIPPED_SPRUCE_LOG,
            Material.STRIPPED_SPRUCE_WOOD,
            Material.STRIPPED_WARPED_HYPHAE,
            Material.STRIPPED_WARPED_STEM,
            Material.ACACIA_LOG,
            Material.BIRCH_LOG,
            Material.DARK_OAK_LOG,
            Material.JUNGLE_LOG,
            Material.SPRUCE_LOG,
            Material.OAK_LOG

    );

    public LocationChecker(Logger logger) {
        this.logger = logger;
    }

    public boolean checkLocation(Location location) {

        this.location = location;

        location.setY((double)(int)location.getY()-1);
        location.setZ((double)(int)location.getZ()-4.0);
        location.setX((double)(int)location.getX()-4.0);

        boolean isValid = true;
        corner = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
        int blockCounter = 1;

        while (blockCounter <81 && isValid) {
            if (woods.contains(location.getBlock().getBlockData().getMaterial())) {

                if (location.getZ()-corner.getZ() < 8) {
                    location.setZ(location.getZ() + 1);
                    blockCounter++;
                } else {
                    location.setX(location.getX()+1);
                    location.setZ(corner.getZ());
                    blockCounter++;
                }
                isValid = true;
            } else {
                isValid = false;
            }
        }
        return isValid;
    }

    public int[] getBounds() {
        return new int[] {
                (int) corner.getX(), (int) corner.getZ(), (int) location.getX(), (int) location.getZ()
        };
    }

    public Tile getClickedTileWithinBoard(Location location, Tile[][] tileMatrix) {

            for (Tile[] tileArray : tileMatrix) {
                for (Tile tile : tileArray) {
                    if (tile.getLocation().getBlockX() == location.getBlockX() && tile.getLocation().getBlockZ() == location.getBlockZ()) {
                        return tile;
                    }
                }
            }

        return null;
    }


}