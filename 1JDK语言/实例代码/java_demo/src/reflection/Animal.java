package reflection;

import java.util.List;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-05
 */

public class Animal {
    private String name;
    private Integer age;
    private List<String> members;

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public Animal(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Animal() {
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Boolean print(String value, Integer qty){
        System.out.println("value: "+value+"; qty: "+qty);
        return true;
    }
}
