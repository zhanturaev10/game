public enum BallSpeed {
    FAST(1),
    NORMAL(20),
    SLOW(100);
    private int speed;

    BallSpeed(int speed) {
        this.speed = speed;
    }
    int speed(){
        return speed;
    }
}
