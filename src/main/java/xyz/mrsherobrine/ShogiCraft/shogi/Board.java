package xyz.mrsherobrine.ShogiCraft.shogi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.UUID;

public class Board {

    private int lowerX;
    private int lowerZ;
    private int upperX;
    private int upperZ;
    private UUID owner;

    public Board(int[] coords, UUID owner) {
        this.lowerX = coords[0];
        this.lowerZ = coords[1];
        this.upperX = coords[2];
        this.upperZ = coords[3];
        this.owner = owner;
    }

    /**
     * Get the board's bounds.
     *
     * @return the lower x and z, then the upper x and z coordinates in the world
     */

    public int[] getBounds() {
        return new int[] {
            this.lowerX, this.lowerZ, this.upperX, this.upperZ
        };
    }

    /**
     *
     * @return lower x and z coordinates of the board
     */

    public int[] getLowerBound() {
        return new int[] {
             this.lowerX, this.lowerZ
        };
    }

    /**
     *
     * @return upper x and z coordinates of the board
     */

    public int[] getUpperBound() {
        return new int[] {
            this.upperX, this.upperZ
        };
    }

    public void createNewBoard(JavaPlugin plugin) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, ()-> {
            plugin.getLogger().info("Created new Board with parameters: "+ Arrays.toString(getBounds())+owner.toString());
            //TODO: insert stuff into db
        });
    }
}
