package i18n;

import java.text.DecimalFormat;

public class MyDecimalFormat {

    public static void main(String[] args) {
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String format = decimalFormat.format(123456789.123);
        System.out.println(format);
    }
}
