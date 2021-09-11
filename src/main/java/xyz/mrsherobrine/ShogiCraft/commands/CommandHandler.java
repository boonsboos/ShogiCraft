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
    public static final Map<UUID, Board> boardList = new HashMap<>();
    public static final Map<UUID, Boolean> isInGame = new HashMap<>();

    private Game game;

    public CommandHandler(JavaPlugin plugin) {
        this.logger = plugin.getLogger();
        this.locCheck = new LocationChecker();
        this.game = new Game();
        this.creator = new ArmorStandCreator();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            logger.info(Arrays.toString(strings));
            if (strings.length != 0) {

                switch (strings[0].toLowerCase()) {
                    case "remove":
                        isInGame.remove(player.getUniqueId());
                        break;
                    case "play":
                       //game.setupGame(boardList.get(player.getUniqueId()));
                        break;
                    case "challenge":

                        //TODO: check for player
                        //TODO: pending challenges
                        if (strings.length == 2) {
                            if (locCheck.checkLocation(player.getLocation()) && !boardList.containsKey(player.getUniqueId())) {
                                boardList.put(player.getUniqueId(), new Board(player.getUniqueId(), player.getLocation()));
                                player.sendMessage("New board has been created at " + Arrays.toString(locCheck.getBounds()));
                                isInGame.put(player.getUniqueId(), true);
                            } else if (boardList.containsKey(player.getUniqueId())) {
                                player.sendMessage(Component.text("You already have a board!", NamedTextColor.RED));
                                break;
                            } else {
                                player.sendMessage(Component.text("Invalid board! Please check if it's all wood and 9x9.", NamedTextColor.RED));
                                break;
                            }
                            if (boardList.containsKey(player.getUniqueId())) {
                                for (int x = 0; x < 9; x++) {
                                    boardList.get(player.getUniqueId()).getBoard()[0][x].setPiece(creator.createPiece("N", boardList.get(player.getUniqueId()).getBoard()[0][x], player.getUniqueId(), 180));
                                    boardList.get(player.getUniqueId()).getBoard()[1][x].setPiece(creator.createPiece("G", boardList.get(player.getUniqueId()).getBoard()[1][x], player.getUniqueId(), 180));
                                    boardList.get(player.getUniqueId()).getBoard()[2][x].setPiece(creator.createPiece("S", boardList.get(player.getUniqueId()).getBoard()[2][x], player.getUniqueId(), 180));
                                    boardList.get(player.getUniqueId()).getBoard()[3][x].setPiece(creator.createPiece("L", boardList.get(player.getUniqueId()).getBoard()[3][x], player.getUniqueId(), 180));
                                    boardList.get(player.getUniqueId()).getBoard()[4][x].setPiece(creator.createPiece("R", boardList.get(player.getUniqueId()).getBoard()[4][x], player.getUniqueId(), 180));
                                    boardList.get(player.getUniqueId()).getBoard()[5][x].setPiece(creator.createPiece("B", boardList.get(player.getUniqueId()).getBoard()[5][x], player.getUniqueId(), 180));
                                    boardList.get(player.getUniqueId()).getBoard()[6][x].setPiece(creator.createPiece("P", boardList.get(player.getUniqueId()).getBoard()[6][x], player.getUniqueId(), 180));
                                    boardList.get(player.getUniqueId()).getBoard()[7][x].setPiece(creator.createPiece("GK", boardList.get(player.getUniqueId()).getBoard()[7][x], player.getUniqueId(), 180));
                                    boardList.get(player.getUniqueId()).getBoard()[8][x].setPiece(creator.createPiece("SK", boardList.get(player.getUniqueId()).getBoard()[8][x], player.getUniqueId(), 180));
                                }

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

    public Map<UUID, Board> getBoardList() {
        return boardList;
    }

}