package enumStudy;

public enum Level {
    HIGH ("High"),
    MEDIUM ("Medium") ,
    LOW ("Low");

    private String levelValue;

    Level(String sValue){
        this.levelValue = sValue;
    }

    public String getLevelValue() {
        return levelValue;
    }
}
