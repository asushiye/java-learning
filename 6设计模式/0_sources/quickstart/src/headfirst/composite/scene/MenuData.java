package headfirst.composite.scene;

/**
 * @author : zhenyun.su
 * @comment : 菜单数据
 * @since : 2019/8/27
 */

public class MenuData {
    private String name;
    private String description;

    public MenuData(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MenuData{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
