package xyz.mrsherobrine.ShogiCraft.listeners;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.mrsherobrine.ShogiCraft.ShogiCraft;
import xyz.mrsherobrine.ShogiCraft.commands.CommandHandler;
import xyz.mrsherobrine.ShogiCraft.shogi.Game;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;
import xyz.mrsherobrine.ShogiCraft.utils.LocationChecker;

import java.util.*;
import java.util.logging.Logger;

public class Listeners implements Listener {

    private LocationChecker checker;
    private CommandHandler commandHandler;
    private Game game;
    private final Logger logger = ShogiCraft.getPlugin(ShogiCraft.class).getLogger();

    private final List<Integer> customModelDataInfo = Arrays.asList(
        1, 2, 4, 8, 9, 10, 12
    );

    public Listeners() {
        this.checker = new LocationChecker();
        this.commandHandler = new CommandHandler(ShogiCraft.getPlugin(ShogiCraft.class));
        this.game = new Game();
    }


    public static final Map<String, Tile> clickedTileList = new HashMap<>();

    private boolean isInList(String uuid) {
        return clickedTileList.containsKey(uuid);
    }

    @EventHandler
    public void movePiece(PlayerInteractEvent event) {

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getHand().equals(EquipmentSlot.HAND) && CommandHandler.turns.containsKey(event.getPlayer().getUniqueId())) {

            //if a player is not sneaking, run normal moves or drops
            if (!event.getPlayer().isSneaking()) {
                if (checker.getClickedTileWithinBoard(event.getClickedBlock().getLocation(), commandHandler.getBoardList().get(event.getPlayer().getUniqueId()).getBoard()) != null && !isInList(event.getPlayer().getUniqueId()+"1")) {
                    if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.PAPER && customModelDataInfo.contains(event.getPlayer().getActiveItem().getItemMeta().getCustomModelData())) {

                        game.drop(
                                checker.getClickedTileWithinBoard(
                                        event.getClickedBlock().getLocation(),
                                        commandHandler.getBoardList().get(event.getPlayer().getUniqueId()).getBoard()
                                ),
                                event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getCustomModelData(),
                                event.getPlayer().getUniqueId()
                        );

                        event.getPlayer().getInventory().getItemInMainHand().subtract();

                    } else {

                        clickedTileList.put(event.getPlayer().getUniqueId() + "1", checker.getClickedTileWithinBoard(event.getClickedBlock().getLocation(), commandHandler.getBoardList().get(event.getPlayer().getUniqueId()).getBoard()));

                        event.getPlayer().showBossBar(BossBar.bossBar(Component.text("You have selected a ").append(game.getFullTypeNameAsComponent(clickedTileList.get(event.getPlayer().getUniqueId()+"1").getPiece().getType())), 0, BossBar.Color.GREEN, BossBar.Overlay.PROGRESS));
                    }
                } else if (checker.getClickedTileWithinBoard(event.getClickedBlock().getLocation(), commandHandler.getBoardList().get(event.getPlayer().getUniqueId()).getBoard()) != null) {

                    clickedTileList.put(event.getPlayer().getUniqueId() + "2", checker.getClickedTileWithinBoard(event.getClickedBlock().getLocation(), commandHandler.getBoardList().get(event.getPlayer().getUniqueId()).getBoard()));

                    game.move(event.getPlayer(), false, commandHandler.getBoardList().get(event.getPlayer().getUniqueId()).getBoard());

                    event.getPlayer().hideBossBar(BossBar.bossBar(Component.text("You have selected a ").append(game.getFullTypeNameAsComponent(clickedTileList.get(event.getPlayer().getUniqueId()+"1").getPiece().getType())), 0, BossBar.Color.GREEN, BossBar.Overlay.PROGRESS));
                }

            //if a player IS sneaking, run the move for promotion. this could be cleaner but idk how to so this is how we're doing it.

            } else {
                if (checker.getClickedTileWithinBoard(event.getClickedBlock().getLocation(), commandHandler.getBoardList().get(event.getPlayer().getUniqueId()).getBoard()) != null && !isInList(event.getPlayer().getUniqueId()+"1")) {

                    clickedTileList.put(event.getPlayer().getUniqueId() + "1", checker.getClickedTileWithinBoard(event.getClickedBlock().getLocation(), commandHandler.getBoardList().get(event.getPlayer().getUniqueId()).getBoard()));

                    event.getPlayer().showBossBar(BossBar.bossBar(Component.text("You have selected a ").append(game.getFullTypeNameAsComponent(clickedTileList.get(event.getPlayer().getUniqueId()+"1").getPiece().getType())), 0, BossBar.Color.GREEN, BossBar.Overlay.PROGRESS));

                } else if (checker.getClickedTileWithinBoard(event.getClickedBlock().getLocation(), commandHandler.getBoardList().get(event.getPlayer().getUniqueId()).getBoard()) != null) {

                    clickedTileList.put(event.getPlayer().getUniqueId() + "2", checker.getClickedTileWithinBoard(event.getClickedBlock().getLocation(), commandHandler.getBoardList().get(event.getPlayer().getUniqueId()).getBoard()));

                    event.getPlayer().hideBossBar(BossBar.bossBar(Component.text("You have selected a ").append(game.getFullTypeNameAsComponent(clickedTileList.get(event.getPlayer().getUniqueId()+"1").getPiece().getType())), 0, BossBar.Color.GREEN, BossBar.Overlay.PROGRESS));

                    game.move(event.getPlayer(), true, commandHandler.getBoardList().get(event.getPlayer().getUniqueId()).getBoard());
                }
            }
        }
    }

}