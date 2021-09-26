package xyz.mrsherobrine.ShogiCraft.shogi.challenge;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.mrsherobrine.ShogiCraft.ShogiCraft;

import java.util.UUID;

public class GameChallenge {

    public void challengeSend(String challenger, String challengee) {

        Bukkit.getPlayer(challenger).sendMessage(Component.text("Challenge sent to ", NamedTextColor.GREEN)
            .append(
                Component.text(challengee, NamedTextColor.AQUA, TextDecoration.BOLD)
            ).append(
                Component.text("!", NamedTextColor.GREEN).decoration(TextDecoration.BOLD, false)
            )
        );

        Bukkit.getPlayer(challengee).sendMessage(
            Component.text(challenger, NamedTextColor.AQUA, TextDecoration.BOLD)
            .append(
                Component.text(" has sent you a challenge.", NamedTextColor.YELLOW).decoration(TextDecoration.BOLD, false)
            ).append(
                Component.newline()
            ).append(
                Component.text("Do you accept? /shogi challenge <accept|deny>", NamedTextColor.YELLOW).decoration(TextDecoration.BOLD, false)
            )
        );
    }

    public void challengeAccept(String challengee, UUID challenger) {

        Player challengerP = Bukkit.getPlayer(challenger);

        challengerP.sendMessage(
            Component.text(challengee, NamedTextColor.AQUA).decorate(TextDecoration.BOLD)
                .append(
                    Component.text(" has accepted your challenge.", NamedTextColor.GREEN).decoration(TextDecoration.BOLD, false)
                )
        );

        Player p = Bukkit.getPlayer(challengee);
        p.sendMessage(Component.text("You will now be warped to your destination!", NamedTextColor.GREEN));
        Bukkit.getScheduler().runTaskLater(ShogiCraft.getPlugin(ShogiCraft.class), ()-> {
            p.teleportAsync(challengerP.getLocation());
        }, 60L);

    }

    public void challengeDeny(String challengee, UUID challenger) {

        Bukkit.getPlayer(challengee).sendMessage(
            Component.text("You have denied", NamedTextColor.RED)
                .append(
                    Component.text(Bukkit.getPlayer(challenger).getName(), NamedTextColor.AQUA, TextDecoration.BOLD)
                )
                .append(
                    Component.text("'s challenge.", NamedTextColor.RED).decoration(TextDecoration.BOLD, false)
                )
        );

        Bukkit.getPlayer(challenger).sendMessage(
            Component.text(challengee, NamedTextColor.AQUA).decorate(TextDecoration.BOLD)
                .append(
                        Component.text(" has denied your challenge. :(", NamedTextColor.RED).decoration(TextDecoration.BOLD, false)
                )
        );

    }

}
