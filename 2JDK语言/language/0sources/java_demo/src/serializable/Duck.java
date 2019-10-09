package serializable;

import java.io.Serializable;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-09
 */

public class Duck implements Serializable {
    static final long serialVersionUID = -623843412345623425L;
    private String name;
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
