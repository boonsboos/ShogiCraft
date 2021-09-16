package xyz.mrsherobrine.ShogiCraft.shogi;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Command;
import org.bukkit.persistence.PersistentDataType;
import xyz.mrsherobrine.ShogiCraft.ShogiCraft;
import xyz.mrsherobrine.ShogiCraft.commands.CommandHandler;
import xyz.mrsherobrine.ShogiCraft.listeners.Listeners;
import xyz.mrsherobrine.ShogiCraft.shogi.enums.Piece;
import xyz.mrsherobrine.ShogiCraft.shogi.enums.Side;
import xyz.mrsherobrine.ShogiCraft.utils.ArmorStandCreator;
import xyz.mrsherobrine.ShogiCraft.utils.LocationChecker;
import xyz.mrsherobrine.ShogiCraft.shogi.enums.PieceType;

import java.util.Map;
import java.util.UUID;

public class Game {

    private ArmorStandCreator creator;
    private LocationChecker checker;

    public Game() {
        this.checker = new LocationChecker();
        this.creator = new ArmorStandCreator();
    }

    public void move(Player player, boolean sneaking, Tile[][] board) {

        Map<String, Tile> tiles = Listeners.clickedTileList;
        Tile from = tiles.get(player.getUniqueId()+"1");
        Tile to = tiles.get(player.getUniqueId()+"2");

        Location toLocation = to.getLocation().toCenterLocation();
        toLocation.setY(to.getLocation().getY());
        toLocation.setYaw(getRoundedAngle((int) from.getLocation().getYaw()));

        //promotion
        if (sneaking) {

                if (from.getPiece() != null && from.getPiece().canMove(from, to, player.getUniqueId()) && !from.getPiece().getType().toString().matches("(K|G)") && CommandHandler.turns.get(player.getUniqueId())) {

                    //check if piece belongs to player who's moving
                    if (to.getPiece() != null && to.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING))) {
                        player.sendMessage(Component.text("You can't take your own pieces!", NamedTextColor.RED));
                        return;
                    }

                    if (to.getPiece() != null) {
                        capture(to.getPiece().getType(), player.getUniqueId());
                        to.getPiece().getEntity().remove();
                    }

                    to.setPiece(from.getPiece());
                    from.getPiece().getEntity().teleportAsync(toLocation);

                    ItemStack item = from.getPiece().getEntity().getItem(EquipmentSlot.HEAD);
                    ItemMeta meta = item.getItemMeta();
                    meta.setCustomModelData(getPromotedTextureFromType(from.getPiece().getType()));
                    item.setItemMeta(meta);
                    from.getPiece().getEntity().setItem(EquipmentSlot.HEAD, item);
                    //TODO this can all be done in a different method

                    from.getPiece().setPromoted(true);
                    from.setPiece(null);

                    CommandHandler.turns.replace(player.getUniqueId(), false);
                    CommandHandler.turns.replace(CommandHandler.challenges.get(player.getUniqueId()), true);

                } else if (!CommandHandler.turns.get(player.getUniqueId())) {
                    player.sendMessage(Component.text("Not your turn!", NamedTextColor.RED));
                } else {
                    player.sendMessage(Component.text("Bad move or can't promote!", NamedTextColor.RED));
                }

        //regular movement
        } else {
            if (from.getPiece() != null && from.getPiece().canMove(from, to, player.getUniqueId()) && CommandHandler.turns.get(player.getUniqueId())) {

                if (to.getPiece() != null && to.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING).equals(from.getPiece().getEntity().getPersistentDataContainer().get(ArmorStandCreator.ownerKey, PersistentDataType.STRING))) {
                    player.sendMessage(Component.text("You can't take your own pieces!", NamedTextColor.RED));
                    return;
                }

                if (to.getPiece() != null) {
                    capture(to.getPiece().getType(), player.getUniqueId());
                    to.getPiece().getEntity().remove();
                }

                to.setPiece(from.getPiece());
                from.getPiece().getEntity().teleportAsync(toLocation);
                from.setPiece(null);

                CommandHandler.turns.replace(player.getUniqueId(), false);
                CommandHandler.turns.replace(CommandHandler.challenges.get(player.getUniqueId()), true);

            } else if (!CommandHandler.turns.get(player.getUniqueId())) {
                player.sendMessage(Component.text("Not your turn!", NamedTextColor.RED));
            } else {
                player.sendMessage(Component.text("Bad move!", NamedTextColor.RED));
            }
        }
        Listeners.clickedTileList.remove(player.getUniqueId()+"1");
        Listeners.clickedTileList.remove(player.getUniqueId()+"2");
    }

    public void capture(PieceType type, UUID uuid) {

        Player p = Bukkit.getPlayer(uuid);

        if (type != PieceType.K) {

            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(getFullTypeNameAsComponent(type));
            meta.setCustomModelData(getTextureFromType(type));
            item.setItemMeta(meta);

            if (p.getInventory().firstEmpty() == -1) {
                Location pLoc = p.getLocation();
                World w = p.getWorld();
                w.dropItemNaturally(pLoc, item);
            } else {
                p.getInventory().addItem(item);
            }
        } else {
            Bukkit.broadcast(
                    Component.text(p.getName(), NamedTextColor.AQUA, TextDecoration.BOLD)
                            .append(
                                    Component.text(" wins against " + Bukkit.getPlayer(CommandHandler.challenges.get(p.getUniqueId())).getName() + "!", NamedTextColor.GREEN).decoration(TextDecoration.BOLD, false)
                            )
            );
        }

    }

    public void drop(Tile destination, int customModelData, UUID uuid) {

        if (destination.getPiece() != null) {
            destination.setPiece(creator.createPiece(getTypeFromTexture(customModelData), destination, uuid, CommandHandler.players.get(uuid).get()));
        } else {
            Bukkit.getPlayer(uuid).sendMessage(Component.text("Can't drop that there!", NamedTextColor.RED));
        }
    }

    //weewoo ugly alert
    public void setupGame(Tile[][] board, UUID player1, UUID player2) {

        //pawns
        for (int x = 0; x < 9; x++) {
            board[2][x].setPiece(creator.createPiece(Piece.P, board[2][x], player1, Side.GOTE.get()));
            board[6][x].setPiece(creator.createPiece(Piece.P, board[6][x], player2, Side.SENTE.get()));
        }

        //kings
        board[8][4].setPiece(creator.createPiece(Piece.SK, board[8][4], player2, Side.SENTE.get()));
        board[0][4].setPiece(creator.createPiece(Piece.GK, board[0][4], player1, Side.GOTE.get()));

        //rooks
        board[1][1].setPiece(creator.createPiece(Piece.R, board[1][1], player1, Side.GOTE.get()));
        board[7][7].setPiece(creator.createPiece(Piece.R, board[7][7], player2, Side.SENTE.get()));

        //bishops
        board[7][1].setPiece(creator.createPiece(Piece.B, board[7][1], player2, Side.SENTE.get()));
        board[1][7].setPiece(creator.createPiece(Piece.B, board[1][7], player1, Side.GOTE.get()));

        //silver
        board[0][2].setPiece(creator.createPiece(Piece.S, board[0][2], player1, Side.GOTE.get()));
        board[0][6].setPiece(creator.createPiece(Piece.S, board[0][6], player1, Side.GOTE.get()));
        board[8][2].setPiece(creator.createPiece(Piece.S, board[8][2], player2, Side.SENTE.get()));
        board[8][6].setPiece(creator.createPiece(Piece.S, board[8][6], player2, Side.SENTE.get()));

        //gold
        board[0][3].setPiece(creator.createPiece(Piece.G, board[0][3], player1, Side.GOTE.get()));
        board[0][5].setPiece(creator.createPiece(Piece.G, board[0][5], player1, Side.GOTE.get()));
        board[8][3].setPiece(creator.createPiece(Piece.G, board[8][3], player2, Side.SENTE.get()));
        board[8][5].setPiece(creator.createPiece(Piece.G, board[8][5], player2, Side.SENTE.get()));

        //knight
        board[0][1].setPiece(creator.createPiece(Piece.N, board[0][1], player1, Side.GOTE.get()));
        board[0][7].setPiece(creator.createPiece(Piece.N, board[0][7], player1, Side.GOTE.get()));
        board[8][1].setPiece(creator.createPiece(Piece.N, board[8][1], player2, Side.SENTE.get()));
        board[8][7].setPiece(creator.createPiece(Piece.N, board[8][7], player2, Side.SENTE.get()));

        //lance
        board[0][0].setPiece(creator.createPiece(Piece.L, board[0][0], player1, Side.GOTE.get()));
        board[0][8].setPiece(creator.createPiece(Piece.L, board[0][8], player1, Side.GOTE.get()));
        board[8][0].setPiece(creator.createPiece(Piece.L, board[8][0], player2, Side.SENTE.get()));
        board[8][8].setPiece(creator.createPiece(Piece.L, board[8][8], player2, Side.SENTE.get()));

    }

    //welcome to utility method land
    public Component getFullTypeNameAsComponent(PieceType type) {
        return switch(type) {
            case B -> Component.text("Bishop").decoration(TextDecoration.ITALIC, false);
            case G -> Component.text("Gold General", NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false);
            case K -> Component.text("King").decoration(TextDecoration.ITALIC, false);
            case L -> Component.text("Lance").decoration(TextDecoration.ITALIC, false);
            case N -> Component.text("Knight").decoration(TextDecoration.ITALIC, false);
            case P -> Component.text("Pawn").decoration(TextDecoration.ITALIC, false);
            case R -> Component.text("Rook").decoration(TextDecoration.ITALIC, false);
            case S -> Component.text("Silver General", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        };
    }

    public Piece getTypeFromTexture(int customModelData) {
        return switch(customModelData) {
            case 1 -> Piece.P;
            case 2 -> Piece.L;
            case 4 -> Piece.R;
            case 8 -> Piece.S;
            case 9 -> Piece.G;
            case 10 -> Piece.N;
            case 12 -> Piece.B;
            default -> throw new IllegalStateException("Unexpected value: " + customModelData);
        };
    }

    public int getTextureFromType(PieceType type) {
        return switch (type) {
            case P -> 1;
            case R -> 4;
            case L -> 2;
            case N -> 10;
            case B -> 12;
            case S -> 8;
            case G -> 9;
            default -> throw new IllegalStateException("Unexpected value: "+ type);
        };
    }

    public int getPromotedTextureFromType(PieceType type) {
        return switch (type) {
            case P -> 5;
            case R -> 6;
            case L -> 7;
            case N -> 11;
            case B -> 13;
            case S -> 14;
            default -> 3;
        };
    }

    public int getRoundedAngle(int angle) {
        if (angle >=180) {
            return 180;
        } else {
            return 0;
        }
    }

}