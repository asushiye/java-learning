package structure.decorator.demo;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/9
 */

public class DefaultTheme implements Theme {
    private String lang;
    private String css;
    private String js;

    public DefaultTheme() {
        this.lang = "zh_CN";
        this.css = "DefaultTheme";
        this.js = "DefaultTheme";
    }

    @Override
    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "DefaultTheme{" +
                "lang='" + lang + '\'' +
                ", css='" + css + '\'' +
                ", js='" + js + '\'' +
                '}';
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public void setJs(String js) {
        this.js = js;
    }
}
