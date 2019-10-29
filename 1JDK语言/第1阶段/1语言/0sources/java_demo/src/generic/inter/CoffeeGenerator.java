package generic.inter;

import java.util.Iterator;
import java.util.Random;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-18
 */

public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    private Class[] types = {Coffee1.class, Coffee2.class, Coffee3.class, Coffee4.class};
    private static Random random = new Random(47);

    public CoffeeGenerator() { }

    public int size(){
        return types.length;
    }

    @Override
    public Coffee next() {
        try {
            return (Coffee)types[random.nextInt(types.length)].newInstance();
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    private int total=0;

    public CoffeeGenerator(int total) {
        this.total = total;
    }
    class CoffeeIterator implements Iterator<Coffee> {
        int count = CoffeeGenerator.this.total;
        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Coffee next() {
            count --;
            return CoffeeGenerator.this.next();
        }

        @Override
        public void remove() {
            throw  new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }
}
