package date;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/9/20
 */

public class Java8Date {
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
