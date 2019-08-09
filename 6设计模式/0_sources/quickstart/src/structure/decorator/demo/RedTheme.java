package structure.decorator.demo;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/9
 */

public class RedTheme extends Changer {
    public RedTheme(Theme theme) {
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
        defaultTheme.setCss("RedCss");
        defaultTheme.setJs("RedJS");
    }
}
