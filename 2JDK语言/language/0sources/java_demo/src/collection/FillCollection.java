package collection;

import java.util.Collection;
import java.util.Map;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-10
 */

public class FillCollection {
    static Collection fill(Collection<String> collection){
        collection.add("rat");
        collection.add("dog");
        collection.add("cat");
        return collection;
    }

    static Map fill(Map<String, String> map){
        map.put("name", "zhenyun.su");
        map.put("country", "china");
        map.put("post", "programmer");
        return map;
    }
}
