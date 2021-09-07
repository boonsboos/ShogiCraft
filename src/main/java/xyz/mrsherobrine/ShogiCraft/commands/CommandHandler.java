package xyz.mrsherobrine.ShogiCraft.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import xyz.mrsherobrine.ShogiCraft.shogi.Board;
import xyz.mrsherobrine.ShogiCraft.shogi.Game;
import xyz.mrsherobrine.ShogiCraft.shogi.Tile;
import xyz.mrsherobrine.ShogiCraft.utils.ArmorStandCreator;
import xyz.mrsherobrine.ShogiCraft.utils.LocationChecker;


import java.util.*;
import java.util.logging.Logger;

public class CommandHandler implements CommandExecutor {

    private Logger logger;

    private LocationChecker locCheck;
    private ArmorStandCreator creator;
    private static final Map<UUID, Tile[][]> boardList = new HashMap<>();
    public static final Map<UUID, Boolean> isInGame = new HashMap<>();

    private Game game;

    public CommandHandler(JavaPlugin plugin) {
        this.logger = plugin.getLogger();
        this.locCheck = new LocationChecker(logger);
        this.game = new Game();
        this.creator = new ArmorStandCreator(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            logger.info(Arrays.toString(strings));
            if (strings.length != 0) {

                switch (strings[0].toLowerCase()) {
                    case "remove":
                        commandSender.sendMessage("not implemented yet lol");
                        break;
                    case "play":
                       //game.setupGame(boardList.get(player.getUniqueId()));
                        break;
                    case "challenge":

                        //TODO: check for player
                        //TODO: pending challenges
                        if (strings.length == 3) {
                            if (locCheck.checkLocation(player.getLocation()) && !boardList.containsKey(player.getUniqueId())) {
                                boardList.put(player.getUniqueId(), new Board().createNewBoard(player.getUniqueId(), player.getLocation()));
                                player.sendMessage("New board has been created at " + Arrays.toString(locCheck.getBounds()));
                            } else if (boardList.containsKey(player.getUniqueId())) {
                                player.sendMessage(Component.text("You already have a board!", NamedTextColor.RED));
                                break;
                            } else {
                                player.sendMessage(Component.text("Invalid board! Please check if it's all wood and 9x9.", NamedTextColor.RED));
                                break;
                            }
                            if (boardList.containsKey(player.getUniqueId())) {
                                for (int x = 0; x < 3; x++) {
                                    boardList.get(player.getUniqueId())[3][x + 3].setPiece(creator.createPiece("N", boardList.get(player.getUniqueId())[3][x + 3], player.getUniqueId(), 0));
                                    boardList.get(player.getUniqueId())[6][x].setPiece(creator.createPiece("N", boardList.get(player.getUniqueId())[6][x], player.getUniqueId(), 0));
                                }
                            /*
                            ItemStack test = new ItemStack(Material.PAPER);
                            ItemMeta testMeta = test.getItemMeta();
                            testMeta.setCustomModelData(9);
                            test.setItemMeta(testMeta);
                            player.getInventory().addItem(test);
                             */
                                //TODO: USE THIS FOR CAPTURE STUFF^
                            } else {
                                player.sendMessage(Component.text("Hey, you don't have a board yet!", NamedTextColor.YELLOW));
                            }
                        } else {
                            return false;
                        }
                        break;
                    case "clear":
                        List<Entity> list = new ArrayList<>();
                        list.addAll(player.getNearbyEntities(16,3,16));
                        for (Entity e : list) {
                            e.remove();
                        }
                        break;
                    default:
                        commandSender.sendMessage(Component.text("Hmm, that doesn't look like a known command to me...", NamedTextColor.RED));
                        return false;
                }

            } else {
                return false;
            }
            return true;
        } else {
            commandSender.sendMessage("Wait a minute, you're not a player!");
            return true;
        }
    }

    public Map<UUID, Tile[][]> getBoardList() {
        return boardList;
    }


}