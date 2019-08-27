package headfirst.composite.scene;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/27
 */

public class MenuItem extends MenuComponent {
    private Object data;
    @Override
    public Object getData(){
        return data;
    }

    public MenuItem(Object data) {
        this.data = data;
    }

    @Override
    public void print(){
        System.out.println(data.toString());
    }
}
