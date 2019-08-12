package structure.flyweight.start;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/12
 */

public class CircleFactory {
    private static final Map<String, Circle> circleMap = new HashMap<>();

    public Circle getCircle(String key){
        Circle circle =  (Circle)circleMap.get(key);
        if (circle == null){
            circle = new Circle(key);
            circleMap.put(key, circle);
            System.out.println("Creating circle of key : "+key);
        }else{
            System.out.println("Getting circle of key : "+key);
        }

        return circle;
    }
}

