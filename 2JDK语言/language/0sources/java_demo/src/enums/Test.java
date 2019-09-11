package enums;

public class Test {
    public static void main(String[] args){
        System.out.println("Level list------------");
        for (Level slevel : Level.values()) {
            System.out.println(slevel);
        }

        Level level = Level.HIGH;
        System.out.println(level+" value is "+level.getLevel());


        System.out.println("Color list------------");
        for (Color color: Color.values()){
            System.out.println(color);
        }

        Color color = Color.YELLOW;
        System.out.println(color+" value is "+color.getColor());
    }
}
