# Java Jackson
		Installation
		ObjectMapper
		Annotations

## Installation

The three JAR files (projects) in the Jackson JSON API are:

* Jackson Core
* Jackson Annotations
* Jackson Databind

Jackson Maven Dependencies

```
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-core</artifactId>
  <version>2.9.5</version>
</dependency>

<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-annotations</artifactId>
  <version>2.9.5</version>
</dependency>

<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.9.5</version>
</dependency>
```

## ObjectMapper

### Read Object From JSON

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


### Write JSON From Objects

```
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


public class Transaction {
    private String type = null;
    private Date date = null;

    public Transaction(String type, Date date) {
        this.type = type;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

 ```

## Jackson Annotations

		Read + Write Annotations
			@JsonIgnore
			@JsonIgnoreProperties
			@JsonIgnoreType
			@JsonAutoDetect
		Read Annotations
			@JsonSetter
			@JsonAnySetter
			@JsonCreator
			@JacksonInject
			@JsonDeserialize
		Write Annotations
			@JsonInclude
			@JsonGetter
			@JsonAnyGetter
			@JsonPropertyOrder
			@JsonRawValue
			@JsonValue
			@JsonSerialize



### @JsonIgnore
```
public class Transaction {
    private String type = null;
    private Date date = null;

    @JsonIgnore
    private String title= null;
    ...
 }
```

### @JsonIgnoreProperties

```
@JsonIgnoreProperties({"field1", "field2"})
public class Transaction {
    private String type = null;
    private Date date = null;

    @JsonIgnore
    private String title= null;

    private String field1= null;
    private String field2= null;

    ...
 }
```

@JsonSetter

取别名，但将对象转换为JSON使用，Field3 转为id

```
    @JsonSetter("id")
    public void setField3(String field3) {
        this.field3 = field3;
    }
```


