package generic.list;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-18
 */

public class RandomList<T> {
    private ArrayList<T>  list = new ArrayList<>();
    private Random random = new Random();
    public void add(T item){
        list.add(item);
    }
    public T select(){
        return list.get(random.nextInt(list.size()));
    }
    public int size(){
        return list.size();
    }

    public boolean remove(T item){
        return list.remove(item);
    }

    @Override
    public String toString(){
        return list.toString();
    }
}
