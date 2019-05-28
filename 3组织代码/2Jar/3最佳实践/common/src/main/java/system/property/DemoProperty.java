package system.property;

import java.io.Serializable;

/**
 * @author : zhenyun.su
 * @since : 2018/11/13
 */

public class DemoProperty implements Serializable {
    private String base;
    private String vipadd;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getVipadd() {
        return vipadd;
    }

    public void setVipadd(String vipadd) {
        this.vipadd = vipadd;
    }
}
