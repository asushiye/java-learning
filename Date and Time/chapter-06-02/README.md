# Java 8 Date Time API

refer to web: https://www.timeanddate.com/time/zones/

		Instant
		Duration
		LocalDate
		LocalDateTime
		LocalTime
		TemporalAdjuster
		ZonedDateTime
		Period
		DateTimeFormatter


## Instant

The Instant class in the Java date time API (java.time.Instant) represents a specific moment on the time line,
Jan. 1st 1970 - 00:00 - Greenwhich mean time (GMT).

* Creating an Instant
* Accessing the Time of an Instant
* Instant Calculations

### Creating an Instant

`Instant now = Instant.now();`

### Accessing the Time of an Instant
An Instant object contains two fields internally which holds the time represented by the Instant:

* Seconds since the epoch.
* Nanoseconds

```
getEpochSecond()
getNano()
```

## Instant Calculations
The Instant class also has several methods which can be used to make calculations relative to an Instant. Some (not all) of these methods are:

* plusSeconds()
* plusMillis()
* plusNanos()
* minusSeconds()
* minusMillis()
* minusNanos()

I will show you two examples below to illustrate how these methods work:

```
Instant now     = Instant.now();
Instant later   = now.plusSeconds(3);
Instant earlier = now.minusSeconds(3);
```

## Java Duration

A Duration object (java.time.Duration) represents a period of time between two Instant objects

* Creating a Duration
* Accessing the Time of a Duration
* Duration Calculations

### Creating a Duration



```
Instant first = Instant.now();
// wait some time while something happens
Instant second = Instant.now();
Duration duration = Duration.between(first, second);
```

### Accessing the Time of a Duration
You can access these two parts of the duration using the Duration methods:

```
getNano()
getSeconds()
```
You can also convert the full time interval Duration to other time units like nanoseconds, minutes, hours or days. You convert a Duration to these time units using these conversion methods:

```
toNanos()
toMillis()
toMinutes()
toHours()
toDays()
````

### Duration Calculations

The Duration class contains a set of methods you can use to perform calculations based on a Duration object. Some of these methods are:

		plusNanos()
		plusMillis()
		plusSeconds()
		plusMinutes()
		plusHours()
		plusDays()
		minusNanos()
		minusMillis()
		minusSeconds()
		minusMinutes()
		minusHours()
		minusDays()

```
Duration start = ... //obtain a start duration
Duration added      = start.plusDays(3);
Duration subtracted = start.minusDays(3);

```

## Java LocalDate
The LocalDate class in the Java 8 date time API represents a local date which is a date without time zone information.

* Creating a LocalDate
* Accessing the Date Information of a LocalDate
* LocalDate Calculations

## Creating a LocalDate
```
LocalDate localDate = LocalDate.now();
LocalDate localDate2 = LocalDate.of(2015, 12, 31);

```
## Accessing the Date Information of a LocalDate

You can access the date information of a LocalDate using these methods:

* getYear()
* getMonth()
* getDayOfMonth()
* getDayOfWeek()
* getDayOfYear()

Here is an example illustrating the use of these methods:

```
int   year       = localDate.getYear();
Month month      = localDate.getMonth();
int   dayOfMonth = localDate.getDayOfMonth();
int   dayOfYear  = localDate.getDayOfYear();
DayOfWeek dayOfWeek = localDate.getDayOfWeek();
```


## LocalDate Calculations

You can perform a set of simple date calculations with the LocalDate class using one or more of the following methods:

		plusDays()
		plusWeeks()
		plusMonths()
		plusYears()
		minusDays()
		minusWeeks()
		minusMonths()
		minusYears()
Here are a few LocalDate calculation examples to give you an idea of how these date calculation methods work:

```
LocalDate localDate = LocalDate.of(2015, 12, 31);
LocalDate localDate1 = localDate.plusYears(3);
LocalDate localDate2 = localDate.minusYears(3);

```

## Java LocalTime

The LocalTime class in the Java 8 date time API represents a specific time of day without any time zone information. For instance, 10.00 AM

* Creating a LocalTime Object
* Accessing the Time of a LocalTime Object
* LocalTime Calculations

### Creating a LocalTime Object
You can create a LocalTime instance in several ways. The first way is to create a LocalTime instance that represents the exact time of now. Here is how that looks:

 `LocalTime localTime = LocalTime.now();`
Another way to create a LocalTime object is to create it from a specific amount of hours, minutes, seconds and nanoseconds. Here is how that looks:

`LocalTime localTime2 = LocalTime.of(21, 30, 59, 11001);`
There are also other versions of the of() method that only takes hours and minutes, or hours, minutes and seconds as parameters.

###  Accessing the Time of a LocalTime Object
You can access the hours, minutes, seconds and nanosecond of a LocalTime object using these methods:

* getHour()
* getMinute()
* getSecond()
* getNano()

### LocalTime Calculations
The LocalTime class contains a set of methods that enable you to perform local time calculations. Some of these methods are:

		plusHours()
		plusMinutes()
		plusSeconds()
		plusNanos()
		minusHours()
		minusMinutes()
		minusSeconds()
		minusNanos()
Here is an example that shows how these methods work:

LocalTime localTime2 = LocalTime.of(21, 30, 59, 11001);

LocalTime localTimeLater   = localTime.plusHours(3);
LocalTime localTimeEarlier = localTime.minusHours(3);


## Java LocalDateTime

* Creating a LocalDateTime
* Accessing the Time of a LocalDateTime
* Date Time Calculations


## Java ZonedDateTime

The ZonedDateTime class in the Java 8 date time API represents a date and time with time zone information

* Creating a ZonedDateTime Object
* Accessing Date and Time of a ZonedDateTime
* Date and Time Calculations
* Time Zones

### Creating a ZonedDateTime Object
You can create a ZonedDateTime object in several ways. The first way is to call the now() method of the ZonedDateTime class. Here is an example of creating a ZonedDateTime object using the now() method:

`ZonedDateTime zonedDateTime = ZonedDateTime.now();`
Another way to create a ZonedDateTime object is to use the of() method which can create a ZonedDateTime object from a concrete date and time. Here is an example of creating a ZonedDateTime object using the of() method:

```
ZoneId zoneId = ZoneId.of("UTC+1");

ZonedDateTime zonedDateTime2 =
    ZonedDateTime.of(2015, 11, 30, 23, 45, 59, 1234, zoneId);
```

### Accessing Date and Time of a ZonedDateTime
You can access the date and time fields of a ZonedDateTime instance using one of the following methods:
```
	getYear()
	getMonth()
	getDayOfMonth()
	getDayOfWeek()
	getDayOfYear()
	getHour()
	getMinute()
	getSecond()
	getNano()
```

Here is an example accessing the year of a ZonedDateTime:

`int year = ZonedDateTime.now().getYear();`
Some of these methods return an enum, and others return an int. From the enums you can return an int representation of their value using their getValue() methods. For instance:

int month = ZonedDateTime.now()
              .getMonth().getValue();


### Date and Time Calculations
The ZonedDateTime class contains a set of methods used for date time calculations. Some of these methods are:

```
	plusYears()
	plusMonths()
	plusDays()
	plusHours()
	plusMinutes
	plusSeconds
	plusNanos()
	minusYears()
	minusMonths()
	minusDays()
	minusHours()
	minusMinutes
	minusSeconds
	minusNanos()
```

Be aware that calculations that span across the daylight savings changes (start or end) may not give the result you expect! An alternative is to use a Period instance, like this:

```
ZonedDateTime newZoneDateTime =
    previousDateTime.plus(Period.ofDays(3));
```

This should result in a more correct calculation.

### Time Zones
The time zones are represented by the ZoneId class as shown in the earlier example. You can create a ZoneId object using the ZoneId.now() method. Here is an example:

`ZoneId zoneId = ZoneId.of("UTC+1");`
The parameter passed to the of() method is the ID of the time zone to create a ZoneId for. In the example above the ID is "UTC+1" which is an offset from UTC (Greenwich) time. You can find the UTC offset for the desired time zone and create an ID matching it by combining "UTC" with the offset (e.g. "+1" or "-5").

You can also use another type of time zone id which consists of the name of the location where the time zone is active. Here is an example:

```
ZoneId zoneId2 = ZoneId.of("Europe/Copenhagen");

ZoneId zoneId3 = ZoneId.of("Europe/Paris");
```

## Java DateTimeFormatter

The Java DateTimeFormatter class is used to parse and format dates represented with the classes in the Java 8 date time API. The DateTimeFormatter is located in the java.time.format

		Predefined DateTimeFormatter Instances
		Formatting a Date

### Predefined DateTimeFormatter Instances

```
	BASIC_ISO_DATE

	ISO_LOCAL_DATE
	ISO_LOCAL_TIME
	ISO_LOCAL_DATE_TIME

	ISO_OFFSET_DATE
	ISO_OFFSET_TIME
	ISO_OFFSET_DATE_TIME

	ISO_ZONED_DATE_TIME
	ISO_INSTANT

	ISO_DATE
	ISO_TIME
	ISO_DATE_TIME

	ISO_ORDINAL_TIME
	ISO_WEEK_DATE

	RFC_1123_DATE_TIME
```

### Formatting a Date

Once you have an instance of a DateTimeFormatter you can format a date using its format() method. Here is a DateTimeFormatter format() example:

```
DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
String formattedDate = formatter.format(LocalDate.now());
System.out.println(formattedDate);
```

```
DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
String formattedZonedDate = formatter.format(ZonedDateTime.now());
System.out.println("formattedZonedDate = " + formattedZonedDate);

，，，

for example



```
import java.time.*;
import java.time.format.DateTimeFormatter;

public class MyDateTime {

    public static void main(String[] args) {
        System.out.println("1、 Instant duration----------------");
        Instant instant1 = Instant.now();
        System.out.println(instant1.getNano());
        System.out.println(instant1.getEpochSecond());
        Instant instant2 = instant1.minusSeconds(3);
        System.out.println(instant2.getEpochSecond());

        Duration duration =  Duration.between(instant1, instant2);
        System.out.println(duration.getSeconds());
        System.out.println(duration.toHours());
        System.out.println(duration.toNanos());

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        // LocalDate localDate =  LocalDate.now();
        System.out.println("2、 LocalDate----------------");
        LocalDate localDate =  LocalDate.of(2018,04,12);

        String formattedDate = formatter.format(localDate);

        System.out.println(formattedDate);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate.getDayOfYear());

        System.out.println(formatter.format(localDate.plusDays(1)));
        System.out.println(formatter.format(localDate.plusMonths(1)));
        System.out.println(formatter.format(localDate.plusWeeks(1)));
        System.out.println(formatter.format(localDate.plusYears(1)));


        System.out.println("3、 LocalTime----------------");
        LocalTime localTime = LocalTime.of(23, 12,12,1334);

        System.out.println(localTime.getHour());
        System.out.println(localTime.getMinute());
        System.out.println(localTime.getSecond());
        System.out.println(localTime.getNano());

        System.out.println("4、 LocalDateTime----------------");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(formatter.format(localDateTime));

        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getHour());
        System.out.println(localDateTime.getMinute());
        System.out.println(localDateTime.getSecond());
        System.out.println(localDateTime.getNano());

        System.out.println("5、 ZoneDateTime----------------");
        DateTimeFormatter formatDateTime = DateTimeFormatter.ISO_DATE_TIME;

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(formatDateTime.format(zonedDateTime));

        ZoneId zoneId1 = ZoneId.of("America/Chicago");
        System.out.println(formatDateTime.format(ZonedDateTime.now(zoneId1)));

        System.out.println(formatDateTime.format(ZonedDateTime.now(ZoneId.of("GMT"))));
        System.out.println(formatDateTime.format(ZonedDateTime.now(ZoneId.of("UTC"))));
        System.out.println(formatDateTime.format(ZonedDateTime.now(ZoneId.of("UTC-2"))));
        System.out.println(formatDateTime.format(ZonedDateTime.now(ZoneId.of("UT"))));


    }
}


```