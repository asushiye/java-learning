import com.alibaba.fastjson.annotation.JSONField;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Car {
    @JSONField(ordinal = 2)
    private String brand;
    @JSONField(ordinal = 1)
    private Integer doors;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",ordinal = 0)
    private ZonedDateTime createDate;
    private Boolean canDrive;
    @JSONField(ordinal = 4)
    private String Field1;
    private String Field2;

    public Boolean getCanDrive() {
        return canDrive;
    }

    public void setCanDrive(Boolean canDrive) {
        this.canDrive = canDrive;
    }

    public String getField1() {
        return Field1;
    }

    public void setField1(String field1) {
        Field1 = field1;
    }

    public String getField2() {
        return Field2;
    }

    public void setField2(String field2) {
        Field2 = field2;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", doors=" + doors +
                ", createDate=" + DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s").format(createDate)+
                ", canDrive=" + canDrive +
                ", Field1='" + Field1 + '\'' +
                ", Field2='" + Field2 + '\'' +
                '}';
    }
}
