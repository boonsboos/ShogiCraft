package xyz.mrsherobrine.ShogiCraft.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import xyz.mrsherobrine.ShogiCraft.shogi.Board;
import xyz.mrsherobrine.ShogiCraft.shogi.Game;
import xyz.mrsherobrine.ShogiCraft.shogi.challenge.GameChallenge;
import xyz.mrsherobrine.ShogiCraft.shogi.enums.Side;
import xyz.mrsherobrine.ShogiCraft.utils.ArmorStandCreator;
import xyz.mrsherobrine.ShogiCraft.utils.LocationChecker;

import java.util.*;
import java.util.logging.Logger;

public class CommandHandler implements CommandExecutor {

    private final Logger logger;
    private final LocationChecker locCheck;
    private final GameChallenge challenge;
    private final Game game;

    //public static NamespacedKey turn = null;

    public static final Map<UUID, Board> boardList = new HashMap<>();
    public static final Map<UUID, UUID> challenges = new HashMap<>();
    public static final Map<UUID, Side> players = new HashMap<>();
    public static final Map<UUID, Boolean> turns = new HashMap<>();

    public CommandHandler(JavaPlugin plugin) {
        this.logger = plugin.getLogger();
        this.locCheck = new LocationChecker();
        this.game = new Game();
        this.challenge = new GameChallenge();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            logger.info(Arrays.toString(strings));
            if (strings.length != 0) {

                switch (strings[0].toLowerCase()) {
                    case "remove":
                        boardList.remove(player.getUniqueId());
                        break;
                    case "create":

                        if (locCheck.checkLocation(player.getLocation()) && !boardList.containsKey(player.getUniqueId())) {

                            boardList.put(player.getUniqueId(), new Board(player.getUniqueId(), player.getLocation()));
                            player.sendMessage("New board has been created at " + Arrays.toString(locCheck.getBounds()));

                        } else if (boardList.containsKey(player.getUniqueId())) {
                            player.sendMessage(Component.text("You have a board! Run /shogi remove if you want to get rid of it.", NamedTextColor.YELLOW));
                        } else {
                            player.sendMessage(Component.text("Invalid board! Please check if it's all wood and 9x9. Are you standing in the center?", NamedTextColor.RED));
                            break;
                        }

                        break;
                    case "challenge":

                        if (strings.length == 2) {

                            //checks if it's valid player/board already exists
                            if (!boardList.containsKey(player.getUniqueId()) && Bukkit.getPlayer(strings[1]) != null) {
                                player.sendMessage(Component.text("Hey! You don't have a board yet! Run /shogi create at your board before you try again", NamedTextColor.YELLOW));
                               break;
                            } else if (Bukkit.getPlayer(strings[1]) != null && !challenges.containsValue(player.getUniqueId()) && boardList.containsKey(player.getUniqueId())) {

                                player.sendMessage(Component.text("Challenge sent!", NamedTextColor.GREEN));

                                //why this? need to have references both ways and cba to do something more logical
                                challenges.put(Bukkit.getPlayerUniqueId(strings[1]), player.getUniqueId());
                                challenges.put(player.getUniqueId(), Bukkit.getPlayerUniqueId(strings[1]));

                                challenge.challengeSend(player.getName(), strings[1]);
                            } else if (Bukkit.getPlayer(strings[1]) == null && !strings[1].matches("(deny|accept)")) {
                                player.sendMessage(Component.text("Player not found or not online!", NamedTextColor.RED));
                                break;
                            }

                            //accepting logic
                            if (strings[1].matches("(accept)") && challenges.containsKey(player.getUniqueId())) {
                                challenge.challengeAccept(player.getName(), challenges.get(player.getUniqueId()));
                                //so i can make sure they're the only ones at that board.
                                boardList.put(player.getUniqueId(), boardList.get(challenges.get(player.getUniqueId())));

                                //handles the initial turns
                                if (Math.random() >= 0.5) {

                                    players.put(challenges.get(player.getUniqueId()), Side.GOTE);
                                    players.put(player.getUniqueId(), Side.SENTE);

                                    Bukkit.getPlayer(challenges.get(player.getUniqueId())).sendMessage(Component.text("You are GOTE."));
                                    player.sendMessage(Component.text("You are SENTE."));

                                    turns.put(challenges.get(player.getUniqueId()), false);
                                    turns.put(player.getUniqueId(), true);

                                } else {

                                    players.put(challenges.get(player.getUniqueId()), Side.SENTE);
                                    players.put(player.getUniqueId(), Side.GOTE);

                                    Bukkit.getPlayer(challenges.get(player.getUniqueId())).sendMessage(Component.text("You are SENTE."));
                                    player.sendMessage(Component.text("You are GOTE."));

                                    turns.put(challenges.get(player.getUniqueId()), true);
                                    turns.put(player.getUniqueId(), false);

                                }

                                if (challenges.containsValue(player.getUniqueId()) && boardList.containsKey(player.getUniqueId())) {
                                    game.setupGame(boardList.get(player.getUniqueId()).getBoard(), challenges.get(player.getUniqueId()), player.getUniqueId());
                                } else {
                                    player.sendMessage(Component.text("Hey, you don't have a board yet!", NamedTextColor.YELLOW));
                                }

                                break;

                            } else if (strings[1].contains("accept") && !challenges.containsKey(player.getUniqueId())) {
                                player.sendMessage(Component.text("You have no challenges!", NamedTextColor.YELLOW));
                                break;
                            }

                            //denying logic
                            if (strings[1].matches("(deny)") && challenges.containsKey(player.getUniqueId())) {
                                challenge.challengeDeny(player.getName(), challenges.get(player.getUniqueId()));
                                challenges.remove(player.getUniqueId());
                                break;
                            } else if (strings[1].contains("deny") && !challenges.containsKey(player.getUniqueId())) {
                                player.sendMessage(Component.text("You have no challenges!", NamedTextColor.YELLOW));
                                break;
                            }

                        } else {
                            return false;
                        }
                        break;
                    case "clear":
                        for (Entity e : player.getNearbyEntities(16, 3, 16)) {
                            if (e instanceof ArmorStand && e.getPersistentDataContainer().has(ArmorStandCreator.ownerKey, PersistentDataType.STRING)) {
                                e.remove();
                            }
                        }
                        player.sendMessage(Component.text("Pieces cleared!", NamedTextColor.GREEN));
                        break;
                    case "resign":
                    case "surrender":
                        //removes players from every list
                        Bukkit.getPlayer(challenges.get(player.getUniqueId())).sendMessage(Component.text(player.getName(), NamedTextColor.AQUA, TextDecoration.BOLD)
                                .append(Component.text(" resigns!", NamedTextColor.YELLOW).decoration(TextDecoration.BOLD, false)));

                        boardList.remove(player.getUniqueId());
                        boardList.remove(challenges.get(player.getUniqueId()));
                        turns.remove(challenges.get(player.getUniqueId()));
                        turns.remove(player.getUniqueId());
                        players.remove(player.getUniqueId());
                        players.remove(challenges.get(player.getUniqueId()));
                        challenges.remove(challenges.get(player.getUniqueId()));
                        challenges.remove(player.getUniqueId());
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