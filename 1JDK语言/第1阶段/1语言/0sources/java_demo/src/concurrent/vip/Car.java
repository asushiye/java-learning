package concurrent.vip;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-31
 */

public class Car {
    private final int id;
    private boolean engine = false, driveTrain = false, wheels = false;
    public Car(int id) {
        this.id = id;
    }
    public synchronized int getId() {
        return id;
    }
    public synchronized void addEngine(String robot) {
        this.engine = true;
        System.out.println(robot+" "+ this);
    }
    public synchronized void addDriveTrain(String robot) {
        this.driveTrain = true;
        System.out.println(robot+" "+ this);
    }
    public synchronized void addWheels(String robot) {
        this.wheels = true;
        System.out.println(robot+" "+ this);
    }
    @Override
    public synchronized String toString() {
        return "Car{" +
                "id=" + id +
                ", engine=" + engine +
                ", driveTrain=" + driveTrain +
                ", wheels=" + wheels +
                '}';
    }
}
