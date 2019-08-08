package creational.build;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/7
 */

public class Product {
    private String name;
    private String title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public void server(){
        System.out.println(this.toString());
    }
}
