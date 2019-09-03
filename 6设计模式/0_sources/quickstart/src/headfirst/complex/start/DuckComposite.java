package headfirst.complex.start;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/9/3
 */

public class DuckComposite {
    private List<Quackable> quackableList = new ArrayList<>();

    public void add(Quackable duck){
        quackableList.add(duck);
    }

    public void quack(){
        Iterator<Quackable> iterator = quackableList.iterator();
        while (iterator.hasNext()){
            iterator.next().quack();
        }
    }
}
