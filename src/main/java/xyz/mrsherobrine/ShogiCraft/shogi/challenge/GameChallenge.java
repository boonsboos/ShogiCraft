package xyz.mrsherobrine.ShogiCraft.shogi.challenge;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;

public class GameChallenge {


    public void challengeSend(String challenger, String challengee) {
        Bukkit.getPlayer(challengee).sendMessage(

                Component.text(challenger, NamedTextColor.AQUA, TextDecoration.BOLD)
                .append(
                        Component.text(" has sent you a challenge.", NamedTextColor.YELLOW).decoration(TextDecoration.BOLD, false)
                ).append(
                        Component.newline()
                ).append(
                        Component.text("Do you accept? /shogi challenge <accept|deny>", NamedTextColor.YELLOW)
                )
        );
    }

    public boolean challengeAccept(String challenger, String challengee) {
        return true;
    }

}
