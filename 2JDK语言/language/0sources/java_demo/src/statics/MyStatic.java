package statics;

/**
 * @author : zhenyun.su
 * @comment : 静态内部类，非静态内部类
 * @since : 2019/9/19
 */

public class MyStatic {
    private String title;
    private NotStaticObject notStaticObject = new NotStaticObject("initValue NotStaticObject");
    private StaticObject staticObject =  new StaticObject("initValue StaticObject");

    public class NotStaticObject{
        private String name;

        public NotStaticObject(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    public static class StaticObject{
        private String name;

        public StaticObject(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NotStaticObject getNotStaticObject() {
        return notStaticObject;
    }

    public void setNotStaticObject(NotStaticObject notStaticObject) {
        this.notStaticObject = notStaticObject;
    }

    public StaticObject getStaticObject() {
        return staticObject;
    }

    public void setStaticObject(StaticObject staticObject) {
        this.staticObject = staticObject;
    }
}
