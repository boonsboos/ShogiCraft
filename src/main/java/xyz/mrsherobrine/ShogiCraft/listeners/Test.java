package xyz.mrsherobrine.ShogiCraft.listeners;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    Map<UUID, Location> movingPlayer = new HashMap<>();
    Map<UUID, ArmorStand> movingEntity = new HashMap<>();
    /*@EventHandler
    public void moveEntity(PlayerInteractEvent event) {

        //TODO only do this if players are in a shogi match

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            plugin.getLogger().info("block.");
            Location armorstandLoc = event.getClickedBlock().getLocation();
            armorstandLoc.setY(armorstandLoc.getY()+1);

            if (armorstandLoc.getNearbyEntities(0.2,0.2,0.2).stream().findFirst().get().getType().equals(EntityType.ARMOR_STAND)) {
                piece = (ArmorStand) armorstandLoc.getNearbyEntities(0.2,0.2,0.2).stream().findFirst().get();
            }

            movingEntity.put(event.getPlayer().getUniqueId(), piece);
            movingPlayer.put(event.getPlayer().getUniqueId(), armorstandLoc);
        }

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && movingPlayer.containsKey(event.getPlayer().getUniqueId())) {
            movingEntity.get(event.getPlayer().getUniqueId()).teleport(event.getClickedBlock().getLocation());
        }
    }*/

}
