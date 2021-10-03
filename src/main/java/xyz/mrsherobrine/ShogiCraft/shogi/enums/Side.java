package xyz.mrsherobrine.ShogiCraft.shogi.enums;

public enum Side {

    GOTE(180),
    SENTE(0);

    private final int sideYaw;

    Side(int side) {
        this.sideYaw = side;
    }

    public int get() {
        return this.sideYaw;
    }

}