package structure.flyweight.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/12
 */

public class Circle implements  Shape{
    private String key;
    private Integer X;
    private Integer Y;
    private Integer Radius;
    private String color;

    public Circle(String key) {
        this.key = key;
    }

    @Override
    public void draw() {
        System.out.println(toString());
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getX() {
        return X;
    }

    public void setX(Integer x) {
        X = x;
    }

    public Integer getY() {
        return Y;
    }

    public void setY(Integer y) {
        Y = y;
    }

    public Integer getRadius() {
        return Radius;
    }

    public void setRadius(Integer radius) {
        Radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "key='" + key + '\'' +
                ", X=" + X +
                ", Y=" + Y +
                ", Radius=" + Radius +
                ", color='" + color + '\'' +
                '}';
    }
}
