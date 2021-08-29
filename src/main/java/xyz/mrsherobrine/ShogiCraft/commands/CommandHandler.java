package xyz.mrsherobrine.ShogiCraft.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import xyz.mrsherobrine.ShogiCraft.shogi.Board;
import xyz.mrsherobrine.ShogiCraft.shogi.Square;
import xyz.mrsherobrine.ShogiCraft.utils.LocationChecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class CommandHandler implements CommandExecutor {

    public JavaPlugin plugin;
    public Logger logger;

    public LocationChecker locCheck;
    public List<Square[][]> boardList = new ArrayList<>();

    public CommandHandler(JavaPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.locCheck = new LocationChecker(logger);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            logger.info(Arrays.toString(strings));
            Player player = (Player) commandSender;
            if (strings.length != 0) {

                //TODO main command handling
                switch (strings[0]) {
                    case "create":
                        if (locCheck.checkLocation(player.getLocation())) {
                            boardList.add(new Board().createNewBoard(plugin, locCheck.getBounds(), player.getUniqueId(), player.getLocation()));
                            player.sendMessage("New board has been created at "+Arrays.toString(locCheck.getBounds()));
                            logger.info("lol have fun reading this:\n"+Arrays.deepToString(boardList.get(0)));
                            // i am hilarious
                        } else {
                            player.sendMessage("Invalid location! Please check if it's all planks and 9x9.");
                        }
                        break;
                    case "remove":
                        commandSender.sendMessage("not implemented yet lol");
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
}
