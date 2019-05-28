package system.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author : zhenyun.su
 * @since : 2019/2/15
 * @comment: 序列化时将string转为json格式
 *
 */

public class StringToJsonSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType,
                      int features) throws IOException {
        serializer.write(JSONObject.parseObject(object.toString()));
    }

}
