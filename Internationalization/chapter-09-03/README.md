# Java Internationalization: Locale
chapter-09-03

		ResourceBundle
		NumberFormat
		DecimalFormat
		DateFormat
		SimpleDateFormat
		Internationalization: Time Zones
		Internationalization: Character Methods


## ResourceBundle

The **java.util.ResourceBundle** class is used to store texts and components that are locale sensitive. 

This text takes a closer look at the ResourceBundle class and its subclasses.

The **ResourceBundle** class has two subclasses called **PropertyResourceBundle** and **ListResourceBundle**. 

The PropertyResourceBundle class stores localized texts in standard Java property files

### Creating a property ResourceBundle

You can use standard property files for storing localized texts. You can load these properties via the ResourceBundle class. Here is an example:

```
Locale locale = new Locale("en", "US");
//Locale locale = new Locale("zh", "CN");
ResourceBundle labels = ResourceBundle.getBundle("i18n.MyBundle", locale);
System.out.println(labels.getString("label1"));
```

The name of a resource bundle is like a class name. 

Thus, i18n.MyBundle means a property file named MyBundle.properties in the package (directory) i18n.

Content of the property file could look like:

>> label1 = Label 1 is done!
>> label2 = Label 2 is through!

Different Languages in Different Property Files

In order to provide strings in different languages, create a property file for each language, 

and suffix them with underscore (_) and then the language code. 

For instance:

* MyBundle.properties
* MyBundle_en.properties
* MyBundle_zh.properties

exmaple

```
    public static void propertyResourceBundle(){
        Locale locale = new Locale("en","US");
        //Locale locale = new Locale("zh","CN");
        ResourceBundle resourceBundle =  ResourceBundle.getBundle("i18n.MyBundle", locale);
        System.out.println(resourceBundle.getString("label1"));

    }
```


### Creating a List ResourceBundle

you create a set of classes with a bundle base name and language suffixes. For instance:

```
i18n.MyClassBundle
i18n.MyClassBundle_en
i18n.MyClassBundle_zh
```


```
import java.util.ListResourceBundle;

public class MyClassBundle extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }
    private Object[][] contents = {
            { "price"   , new Double(10.00) },
            { "currency", "EUR" },
    };
}
```


```
public class MyClassBundle_zh extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return contents;
    }
    private Object[][] contents = {
            { "price"   , new Double(13.00) },
            { "currency", "RMB" },
    };
}
```

```
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
```


Getting Values From a ResourceBundle

```
getObject(String key);
getString(String key);
getStringArray(String key);
```

## NumberFormat

```
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

 ```

## DecimalFormat


Number Format Pattern Syntax
You can use the following characters in the formatting pattern:

> 0	A digit - always displayed, even if number has less digits (then 0 is displayed)
> #	A digit, leading zeroes are omitted.
> .	Marks decimal separator
> ,	Marks grouping separator (e.g. thousand separator)
> E	Marks separation of mantissa and exponent for exponential formats.
> ;	Separates formats
> -	Marks the negative number prefix
> %	Multiplies by 100 and shows number as percentage
> ?	Multiplies by 1000 and shows number as per mille
> ¤	Currency sign - replaced by the currency sign for the Locale. Also makes formatting use the monetary decimal separator instead of normal decimal separator. ¤¤ makes formatting use international monetary symbols.
> X	Marks a character to be used in number prefix or suffix
> '	Marks a quote around special characters in prefix or suffix of formatted number.


Notice that some numbers are rounded, just like with a NumberFormat instance.

```
    public static void main(String[] args) {
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String format = decimalFormat.format(123456789.123);
        System.out.println(format);
    }
```

Here are a few examples, formatted using a UK Locale:

|Pattern	|Number	|Formatted String|
|-|-|-|
|###.###	|123.456	|123.456|
|###.#	        |123.456	|123.5|
|###,###.##	|123456.789	|123,456.79|
|000.###	|9.95	|009.95|
|##0.###	|0.95	|0.95|


## DateFormat



### Formatting Dates

```
public class MyDateFormat {
    public static void main(String[] args) {
       // Locale locale = new Locale("en", "US");  // Thursday, April 26, 2018
        Locale locale = new Locale("zh", "CN");  // 2018年4月26日 星期四
        DateFormat dateFormat =  DateFormat.getDateInstance(DateFormat.FULL, locale);
        String sDate = dateFormat.format(new Date());
        System.out.println(sDate);
    }
}
```

The date format parameter can be chosen among the following constants in the DateFormat class:

DateFormat.DEFAULT
DateFormat.SHORT
DateFormat.MEDIUM
DateFormat.LONG
DateFormat.FULL

### Formatting Times

```
Locale locale = new Locale("da", "DK");
DateFormat dateFormat = DateFormat.getTimeInstance(
                            DateFormat.DEFAULT, locale);

String date = dateFormat.format(new Date());
System.out.println(date);
```

### Formatting Date and Time

```
Locale locale = new Locale("da", "DK");
DateFormat dateFormat = DateFormat.getDateTimeInstance(
        DateFormat.DEFAULT,DateFormat.DEFAULT, locale);

String date = dateFormat.format(new Date());
System.out.println(date);
```

##  SimpleDateFormat

### Formatting Dates

```
String pattern = "yyyy-MM-dd";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

String date = simpleDateFormat.format(new Date());
System.out.println(date);
```

Pattern Examples
Here are a few patterns examples:

|Pattern	|Example|
|-|-|
|dd-MM-yy	|31-01-12|
|dd-MM-yyyy	|31-01-2012|
|MM-dd-yyyy	|01-31-2012|
|yyyy-MM-dd	|2012-01-31|
|yyyy-MM-dd HH:mm:ss	|2012-01-31 23:59:59|
|yyyy-MM-dd HH:mm:ss.SSS	|2012-01-31 23:59:59.999|
|yyyy-MM-dd HH:mm:ss.SSSZ	|2012-01-31 23:59:59.999+0100|
|EEEEE MMMMM yyyy HH:mm:ss.SSSZ	|Saturday November 2012 10:45:42.720+0100|


## Time Zones

This text examines how to work with time zones in Java.

When users of a system, e.g. a web application, have different time zones, 

it can be challenging to manage dates and time in the system in manner that looks consistent to all users. 

I will take a look at this issue in this text.

### UTC
UTC is short for Coordinated Universal Time (Universal Time Coordinated ?). UTC is the time in the UK time zone (like Greenwich Mean Time).

### Time Zones
All time zones are calculated as offsets to UTC time. 
For instance, the time zone in Copenhagen, Denmark is UTC + 1 meaning UTC time plus one hour.


## Character Methods

getType()
isDefined()

```
char aChar = 'C';

Character.isDigit (aChar);        // returns false
Character.isLetter(aChar);        // returns true
Character.isLetterOrDigit(aChar); // returns true
Character.isLowerCase(aChar);     // returns false
Character.isUpperCase(aChar);     // returns true
Character.isSpaceChar(aChar);     // returns false
```

```
char aChar = 'æ';
int type = Character.getType(aChar);

if(type == Character.LOWERCASE_LETTER) { ... }
if(type == Character.UPPERCASE_LETTER) { ... }
```

```
char aChar = 'A';
int  anInt = aChar;

bolean isDefined = Character.isDefined(anInt);
```

##  Converting to and from Unicode

```
byte[] bytes = new byte[10];

String str = new String(bytes, Charset.forName("UTF-8"));

System.out.println(str);
```