package reflection;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-05
 */

public class Animal {
    private String name;
    private Integer age;

    public Animal(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Animal(String name) {
        this.name = name;
    }
    public Animal() {
    }

    @Override
    public String toString() {
        return "Animal name is "+name;
    }
}
