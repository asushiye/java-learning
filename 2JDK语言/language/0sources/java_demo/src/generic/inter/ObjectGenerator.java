package generic.inter;

/**
 * @author : zhenyun.su
 * @comment : 通用生成器
 * @since : 2019-10-18
 */

public class ObjectGenerator {
    public static <T> T create(Class<T> t){
        try{
            return t.newInstance();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    };
}
