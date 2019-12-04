import java.time.LocalDateTime;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-12-04
 */

public class Test {
    public static void main(String[] args) {
        cityTest();
//        cityBuilderTest();
    }
    public static void cityTest() {
        City city = new City();
        city.setId(1L);
        city.setCode("101");
        city.setCreatedDate(LocalDateTime.now());
        city.setName("上海");
        System.out.println(city.toString());
        System.out.println(city);
        System.out.println(city.hashCode());
        System.out.println(city.equals(new City(2L, "102")));
    }
    public static void cityBuilderTest() {
        CityBuilder city = new CityBuilder(2L, "102","北京", LocalDateTime.now());
        System.out.println(city.toString());
    }

}
