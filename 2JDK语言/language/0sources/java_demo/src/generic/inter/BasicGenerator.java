package generic.inter;

/**
 * @author : zhenyun.su
 * @comment : 通用生成器
 * @since : 2019-10-21
 */

public class BasicGenerator<T> implements Generator<T> {
    private Class<T> type;

    public BasicGenerator(Class<T> type) {
        this.type = type;
    }

    @Override
    public T next() {
        try{
            return type.newInstance();
        }catch(Exception e){
            throw  new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type){
        return new BasicGenerator<T>(type);
    }
}
