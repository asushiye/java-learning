package enums;

public enum Color {
    RED ("red"),
    BLUE("blue") ,
    YELLOW ("yellow");

    private String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
