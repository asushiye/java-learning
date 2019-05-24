package i18n;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

//        DateFormat.DEFAULT
//        DateFormat.SHORT
//        DateFormat.MEDIUM
//        DateFormat.LONG
//        DateFormat.FULL

public class MyDateFormat {
    public static void main(String[] args) {
       // Locale locale = new Locale("en", "US");  // Thursday, April 26, 2018
        Locale locale = new Locale("zh", "CN");  // 2018年4月26日 星期四
        DateFormat dateFormat =  DateFormat.getDateInstance(DateFormat.FULL, locale);
        String sDate = dateFormat.format(new Date());
        System.out.println(sDate);
    }
}
