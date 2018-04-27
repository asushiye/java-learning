import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class MyFastJson {

    public static void main(String[] args) {
        System.out.println(JSON.defaultLocale);
        System.out.println(JSON.defaultTimeZone);

        MyFastJson.writeJsonFromObject();
        MyFastJson.readObjectFromJson();
    }

    public static void writeJsonFromObject(){
        Car car = new Car();
        car.setBrand("daphne");
        car.setDoors(4);
        car.setCreateDate(ZonedDateTime.now());
        car.setCanDrive(true);
        car.setField1("");
        car.setField2(null);

        String carJson = JSON.toJSONString(car);
        // {"brand":"daphne","canDrive":true,"createDate":"2018-04-27 14:30:04","doors":4,"field1":""}
        System.out.println(carJson);
        String carJson2 = JSON.toJSONString(car,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullListAsEmpty
        );
        System.out.println(carJson2);

        String carStringArray=  JSON.toJSONString(car, SerializerFeature.BeanToArray);
        System.out.println(carStringArray);
    }

    public static void readObjectFromJson(){
        String carJson = "{\"brand\":\"daphne\",\"canDrive\":true,\"createDate\":\"2018-04-27 14:34:13\",\"doors\":4,\"field1\":\"\"}";
        Car car = JSON.parseObject(carJson, Car.class);
        System.out.println(car.toString());

        String carJsons = "[{\"brand\":\"daphne\",\"canDrive\":true,\"createDate\":\"2018-04-27 14:34:13\",\"doors\":4,\"field1\":\"\"},{\"brand\":\"shoebox\",\"canDrive\":true,\"createDate\":\"2019-04-27 14:34:13\",\"doors\":6,\"field1\":\"\"}]";

        List<Car> cars = JSON.parseArray(carJsons, Car.class);
        System.out.println("car count: "+cars.size());
        for (Car item: cars){
            System.out.println(item.toString());
        }
    }

}
