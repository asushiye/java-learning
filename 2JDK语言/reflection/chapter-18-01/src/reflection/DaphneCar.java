package reflection;

import java.time.ZonedDateTime;
import java.util.List;

@MyTypeAnnotation(name = "Annotation", value = "Class")
public class DaphneCar extends Car implements Drivable {

    @MyFieldAnnotation(name = "Annotation", value = "Field")
    public String address;


    public DaphneCar(String address) {
        this.address = address;
    }

    @Override
    @MyMethodAnnotation(name="Annotation", value = "Method")
    public void drive(@MyParameterAnnotation(name = "Annotation", value = "Parameter") String name) {
        System.out.println("drive car is "+name);
    }

    public List<String> stringList;

    public List<String> getStringList(){
        return this.stringList;
    }

    public void setStringList(List<String> value){
        this.stringList = value;
    }

    private static void addOil() {
        System.out.println("gei oil for me");
    }

    public DaphneCar(String name, Integer door, String address) {
        this.address = address;
        super.setBrand("daphne");
        super.setCreate_date(ZonedDateTime.now());
        super.setDoor(door);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
