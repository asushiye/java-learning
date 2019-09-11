# Java Inheritance
    Java Inheritance
    type cast
    Calling Superclass Methods
    The instanceof Instruction

## Java Inheritance

```java
public class Vehicle {
    protected String licensePlate = null;
    public void setLicensePlate(String license) {
        this.licensePlate = license;
    }
}

public class Car extends Vehicle {
    int numberOfSeats = 0;
    public String getNumberOfSeats() {
        return this.numberOfSeats;
    }
}
```

## inheritance and type cast

### type cast
``
Car car = new Car();
Vehicle vehicle = car;
``

### upcasting and downcasting
```java
Car car= new Car();
// upcast to Vehicle
Vehicle vehicle = car;

// downcast to car again
Car car2=  (Car) vehicle;
```

## Calling Superclass Methods
```java
public class Car extends Vehicle {

    public void setLicensePlate(String license) {
        super.setLicensePlate(license);
    }
}
```

Calling Superclass Methods
## The instanceof Instruction

```
Car car = new Car();
boolean isCar = car instanceof Car;  //true

Car car = new Car();
boolean isVehicle = car instanceof Vehicle; //true

Truck truck = new Truck();
Vehicle vehicle = truck;
boolean isCar = vehicle instanceof Car; //false
```
