package i18n;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class MyNumberFormat {


    public static void main(String[] args) {
        Locale locale = new Locale("zh", "CN");
        // Locale locale = new Locale("en", "US");

        // Formatting Numbers
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        String number = numberFormat.format(10000.99);
        System.out.println(number);

        // Formatting Currencies
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        String currency = currencyFormat.format(100.999);
        System.out.println(currency);

        currencyFormat.setCurrency(Currency.getInstance("EUR"));
        String currency1 = currencyFormat.format(100.999);
        System.out.println(currency1);

        // Formatting Percentages
        NumberFormat percentageFormat = NumberFormat.getPercentInstance(locale);
        String percentage = percentageFormat.format(99.999);
        System.out.println(percentage);
    }



}