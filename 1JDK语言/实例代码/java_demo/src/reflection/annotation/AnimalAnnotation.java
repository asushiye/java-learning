package reflection.annotation;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-05
 */
@MyTypeAnnotation(name="myType", value="class")
public class AnimalAnnotation {
    @MyFieldAnnotation(name="myField", value="field")
    private String name;
    private Integer age;

    public AnimalAnnotation(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public AnimalAnnotation() {
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @MyMethodAnnotation(name="myMethod", value="method")
    public Boolean print(@MyParameterAnnotation(name="myParam", value="param") String value, Integer qty){
        System.out.println("value: "+value+"; qty: "+qty);
        return true;
    }
}
