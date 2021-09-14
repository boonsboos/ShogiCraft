package xyz.mrsherobrine.ShogiCraft.shogi;

public enum Side {

    GOTE(0),
    SENTE(180);

    private final int sideYaw;

    Side(int side) {
        this.sideYaw = side;
    }

    public int getAngle() {
        return this.sideYaw;
    }

}