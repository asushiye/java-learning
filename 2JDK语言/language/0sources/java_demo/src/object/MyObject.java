package object;

import java.util.Objects;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/9/17
 */

public class MyObject extends Object {
    private String name;
    private String key;

    public MyObject() {
    }

    public MyObject(String name, String key) {
        this.name = name;
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyObject)) return false;
        MyObject myObject = (MyObject) o;
        return Objects.equals(name, myObject.name) &&
                Objects.equals(key, myObject.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, key);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
        super.finalize();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
