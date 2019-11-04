package serializable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-09
 */

public class Duck implements Serializable {
    static final long serialVersionUID = -623843412345623425L;
    private String name;
    private ArrayList dd;
    private Set d;
    private Queue e;
    private HashMap e1;
    public void print(){
        System.out.println(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
