package date;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/9/20
 */

public class DateUtils8Test {
    public static void main(String[] args) throws Exception {
        System.out.println("------------------zoneid---------------------");
        ZoneId zoneId = ZoneId.systemDefault();
        ZoneId zoneId1 = ZoneId.of("Asia/Shanghai");
        ZoneId zoneId2 = ZoneId.of("UTC+9");
        System.out.println(zoneId.toString());
        System.out.println(zoneId1.toString());
        System.out.println(zoneId2.toString());

        System.out.println("------------------instant---------------------");
        Instant instant = Instant.now();
        System.out.println(instant.getEpochSecond());
        System.out.println(instant.getNano());
        System.out.println(instant.toString());
        System.out.println(instant.toEpochMilli());

        long int1 = instant.getEpochSecond();
        long int2 = instant.toEpochMilli();

        System.out.println(DateUtils7.getDateStringBySecond(int1, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtils7.getDateStringByMillisecond(int2, "yyyy-MM-dd HH:mm:ss.SSS z E"));
        System.out.println("------------------duration---------------------");
        Instant instant1 = instant.plusSeconds(1 * 24 * 60 * 60).plusNanos(23 * 1000000);
        Duration duration = Duration.between(instant, instant1);
        duration = duration.plusDays(3);
        System.out.println(duration.toString());
        System.out.println(duration.getSeconds());
        System.out.println(duration.getNano());
        System.out.println(duration.toDays());
        System.out.println(duration.toHours());
        System.out.println(duration.toMinutes());
        System.out.println(duration.toMillis());

        System.out.println("------------------localDateTime---------------------");
        LocalDateTime localDateTime3 = LocalDateTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(Instant.now(), zoneId);
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, 01, 01, 13, 0, 3, 23000000);

        System.out.println(localDateTime1.toString());
        System.out.println(localDateTime1.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(localDateTime2.toString());
        System.out.println(localDateTime3.toString());

        System.out.println(localDateTime1.getYear());
        System.out.println(localDateTime1.getMonth());
        System.out.println(localDateTime1.getMonth().getValue());
        System.out.println(localDateTime1.getDayOfMonth());
        System.out.println(localDateTime1.getHour());
        System.out.println(localDateTime1.getMinute());
        System.out.println(localDateTime1.getSecond());
        System.out.println(localDateTime1.getNano());
        System.out.println(localDateTime1.getDayOfYear());
        System.out.println(localDateTime1.getDayOfWeek());
        System.out.println(localDateTime1.getDayOfWeek().getValue());

        System.out.println(localDateTime1.toLocalDate().toString());
        System.out.println(localDateTime1.toLocalTime().toString());
        ZonedDateTime zonedDateTime = localDateTime1.atZone(zoneId);
        System.out.println(zonedDateTime.toInstant().toEpochMilli());

        localDateTime1 = localDateTime1.plusHours(12);
        System.out.println(localDateTime1.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        localDateTime1 = localDateTime1.minusHours(12);
        System.out.println(localDateTime1.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        System.out.println("------------------ZonedDateTime---------------------");
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(2019, 01, 01, 13, 0, 3, 23000000, zoneId);
        ZonedDateTime zonedDateTime2 = ZonedDateTime.now(zoneId);
        ZonedDateTime zonedDateTime3 = ZonedDateTime.ofInstant(Instant.now(), zoneId);
        System.out.println(zonedDateTime1.toString());
        System.out.println(zonedDateTime2.toString());
        System.out.println(zonedDateTime3.toString());

        Instant instant2= zonedDateTime1.toInstant();
        System.out.println(zonedDateTime1.toLocalDateTime().toString());
        System.out.println(instant2.toEpochMilli());
        ZoneId zoneId4 = zonedDateTime1.getZone();
        System.out.println(zonedDateTime1.getYear());

        System.out.println("------------------DateTimeFormatter---------------------");
        System.out.println(DateTimeFormatter.ISO_INSTANT.format(instant));
        System.out.println(zonedDateTime1.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));

        DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zonedDateTime1);

        System.out.println(localDateTime1.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(localDateTime1.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(localDateTime1.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        System.out.println(zonedDateTime1.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(zonedDateTime1.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(zonedDateTime1.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        System.out.println("------------------ofPattern---------------------");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:s.SSS");
        System.out.println(localDateTime1.format(dateFormatter));
        System.out.println(zonedDateTime1.format(dateFormatter));

        LocalDateTime localDateTime = LocalDateTime.parse("2019-01-01 14:01:25.100", dateFormatter);
        System.out.println(localDateTime.toString());

    }
}