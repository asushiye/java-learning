package i18n;

import sun.rmi.runtime.NewThreadAction;

import java.util.Locale;
import java.util.ResourceBundle;

public class MyResourceBundle {

    public static void main(String[] args) {
        MyResourceBundle.propertyResourceBundle();
        MyResourceBundle.listResourecBundle();
    }

    public static void propertyResourceBundle(){
        Locale locale = new Locale("en","US");
        //Locale locale = new Locale("zh","CN");
        ResourceBundle resourceBundle =  ResourceBundle.getBundle("i18n.MyBundle", locale);
        System.out.println(resourceBundle.getString("label1"));

    }

    public static void listResourecBundle(){
        Locale locale = new Locale("en", "US"); //no bundle for German -> default
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.MyClassBundle", locale);

        System.out.println("price   : " + bundle.getObject("price"));
        System.out.println("currency: " + bundle.getObject("currency"));

        locale = new Locale("zh", "CN");
        bundle = ResourceBundle.getBundle("i18n.MyClassBundle_zh", locale);
        System.out.println("price   : " + bundle.getObject("price"));
        System.out.println("currency: " + bundle.getObject("currency"));
    }
}
