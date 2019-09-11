package enums;

public enum Level {
    HIGH (3),
    MEDIUM (2) ,
    LOW (1);

    private int level;

    Level(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
