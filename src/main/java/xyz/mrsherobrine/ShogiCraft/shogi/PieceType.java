package xyz.mrsherobrine.ShogiCraft.shogi;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;

public enum PieceType {

    B, //bishop
    G, //gold
    K, //king
    L, //lance
    N, //knight
    P, //pawn
    R, //rook
    S;  //silver

    public Component getTypeAsComponent(PieceType type) {

        return switch(type) {
            case B -> Component.text("Bishop").decoration(TextDecoration.ITALIC, false);
            case G -> Component.text("Gold General").decoration(TextDecoration.ITALIC, false);
            case K -> null;
            case L -> null;
            case N -> null;
            case P -> null;
            case R -> null;
            case S -> null;
        };
    }

}