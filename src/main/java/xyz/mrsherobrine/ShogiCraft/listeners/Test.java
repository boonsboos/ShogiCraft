package xyz.mrsherobrine.ShogiCraft.listeners;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.mrsherobrine.ShogiCraft.shogi.Board;

import java.util.Objects;

public class Test implements Listener {

    public JavaPlugin plugin;
    public Test(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /*@EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        new Board(new int[]{0,0,9,9}, joinEvent.getPlayer().getUniqueId()).createNewBoard(plugin);
    }*/

    ArmorStand piece;
    Player player;

    @EventHandler
    public void getPiece(PlayerArmorStandManipulateEvent pasmevent) {
        player = pasmevent.getPlayer();
        piece = pasmevent.getRightClicked();
        plugin.getLogger().info("got entity event!");
    }

    @EventHandler
    public void moveEntity(PlayerInteractEvent event) {
        //TODO only do this if players are in a shogi match
        plugin.getLogger().info("event!");
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) /*&& event.getPlayer().hasLineOfSight(piece)*/) {
            plugin.getLogger().info("Right clicked air.");
            Location block = Objects.requireNonNull(event.getClickedBlock()).getLocation();
            piece.teleport(block.add(0, 1, 0));
        }
    }

}
