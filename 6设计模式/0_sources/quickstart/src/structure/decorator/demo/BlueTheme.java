package structure.decorator.demo;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/9
 */

public class BlueTheme extends Changer {
    public BlueTheme(Theme theme) {
        super(theme);
    }

    @Override
    public void display() {
        this.setContent();
        super.display();
    }

    public void setContent(){
        DefaultTheme defaultTheme = ((DefaultTheme)super.theme);
        defaultTheme.setLang("en_EN");
        defaultTheme.setCss("BlueCss");
        defaultTheme.setJs("BlueJS");
    }
}
