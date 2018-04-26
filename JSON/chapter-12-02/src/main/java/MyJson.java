import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

public class MyJson {

    private static final String FILE_NAME = "E:\\6_Java\\4_workdemo\\Java-language-learning\\JSON\\chapter-12-02\\src\\main\\java\\file.json";
    private static final String FILE_PATH = "E:\\6_Java\\4_workdemo\\Java-language-learning\\JSON\\chapter-12-02\\src\\main\\java\\";

    public static void main(String[] args) {
        System.out.println("---MyJson.readObjectFromJSON()------------------");
        MyJson.readObjectFromJSON();
        System.out.println("---MyJson.writeJSONFromObjects------------------");
        MyJson.writeJSONFromObjects();
    }


    public static void readObjectFromJSON(){
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        try{
            ObjectMapper objectMapper = new ObjectMapper();

            // From JSON String
            Car car = objectMapper.readValue(carJson, Car.class);

            // From JSON Reader
            Reader reader =  new StringReader(carJson);
            Car car1 = objectMapper.readValue(reader, Car.class);

            // Form Json FIle
            File file = new File(FILE_NAME);
            Car car2 = objectMapper.readValue(file, Car.class);

            // From JSON InputStream
            InputStream inputStream =  new FileInputStream(FILE_NAME);
            Car car3 = objectMapper.readValue(inputStream, Car.class);

            // From JSON Byte Array
            Car car4 = objectMapper.readValue(carJson.getBytes("UTF-8"), Car.class);

            // Read Object Array From JSON Array String
            String jsonArray = "[{\"brand\":\"ford\",\"doors\" : 5}, {\"brand\":\"Fiat\"}]";
            Car[] cars = objectMapper.readValue(jsonArray, Car[].class);
            for(Car item: cars){
                System.out.println(item.brand);
                System.out.println(item.doors);
            }

            // Read Object List  From JSON Array String
            List<Car> cars1 = objectMapper.readValue(jsonArray, new TypeReference<List<Car>>(){});
            Iterator<Car> iterator = cars1.iterator();
            while (iterator.hasNext()) {
                Car car5 =  iterator.next();
                System.out.println(car5.brand);
                System.out.println(car5.doors);
            }

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void writeJSONFromObjects(){
        try{
            Car car = new Car();
            car.setBrand("daphne");
            car.setDoors("4");
            ObjectMapper objectMapper = new ObjectMapper();

            // write Json String from Object
            String carJson = objectMapper.writeValueAsString(car);
            System.out.println(carJson);

            // write Json String from Object
            FileOutputStream outputStream =  new FileOutputStream(FILE_PATH+"writeFile.JSON");
            objectMapper.writeValue(outputStream, car);

            Transaction transaction = new Transaction("transfer", new Date());
            ObjectMapper oMapper = new ObjectMapper();
            String output = objectMapper.writeValueAsString(transaction);
            System.out.println(output);

            // date to string
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            oMapper.setDateFormat(dateFormat);
            String output1 = objectMapper.writeValueAsString(transaction);
            System.out.println(output1);


        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

}
