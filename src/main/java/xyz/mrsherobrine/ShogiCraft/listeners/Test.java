package xyz.mrsherobrine.ShogiCraft.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.mrsherobrine.ShogiCraft.commands.CommandHandler;
import xyz.mrsherobrine.ShogiCraft.shogi.Game;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;
import xyz.mrsherobrine.ShogiCraft.utils.LocationChecker;

import java.util.HashMap;
import java.util.Map;

public class Test implements Listener {

    private JavaPlugin plugin;

    private LocationChecker checker;
    private CommandHandler commandHandler;
    private Game game;

    public Test(JavaPlugin plugin) {
        this.plugin = plugin;
        this.checker = new LocationChecker(plugin.getLogger());
        this.commandHandler = new CommandHandler(plugin);
        this.game = new Game();
    }


    public static final Map<String, Tile> clickedTileList = new HashMap<>();

    private boolean isInList = false;

    @EventHandler
    public void moveEntity(PlayerInteractEvent event) {

        //TODO only do this if players are in a shogi match

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getHand().equals(EquipmentSlot.HAND)) {
            if (checker.getClickedTileWithinBoard(event.getClickedBlock().getLocation(), commandHandler.getBoardList().get(event.getPlayer().getUniqueId())) != null  && !isInList) {
                clickedTileList.put(event.getPlayer().getUniqueId()+"1", checker.getClickedTileWithinBoard(event.getClickedBlock().getLocation(), commandHandler.getBoardList().get(event.getPlayer().getUniqueId())));
                isInList = true;
            } else  if(checker.getClickedTileWithinBoard(event.getClickedBlock().getLocation(), commandHandler.getBoardList().get(event.getPlayer().getUniqueId())) !=null ) {
                clickedTileList.put(event.getPlayer().getUniqueId() +"2", checker.getClickedTileWithinBoard(event.getClickedBlock().getLocation(), commandHandler.getBoardList().get(event.getPlayer().getUniqueId())));
                game.move(event.getPlayer());
                isInList = false;
            }
        }
    }

    public Map<String, Tile> getClickedTileList() {
        return clickedTileList;
    }


}
