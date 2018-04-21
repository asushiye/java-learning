import java.util.concurrent.TimeUnit;

public class MyTimeUnit {

    private TimeUnit timeUnit;

    public static void main(String[] args){

        try
        {
            MyTimeUnit myTimeUnit = new MyTimeUnit();
            myTimeUnit.timeUnitInfo(Long.valueOf(1), TimeUnit.DAYS );

            TimeUnit.SECONDS.sleep(2);

            myTimeUnit.timeUnitInfo(Long.valueOf(1), TimeUnit.HOURS);

        }catch(InterruptedException e){
            System.out.println(e.getStackTrace());
        }
    }

    public  void timeUnitInfo(Long time, TimeUnit unit){
        timeUnit = unit;
        System.out.println(timeUnit.name());
        System.out.println(timeUnit.toDays(time));
        System.out.println(timeUnit.toHours(time));
        System.out.println(timeUnit.toMinutes(time));
        System.out.println(timeUnit.toSeconds(time));
        System.out.println(timeUnit.toMillis(time));
        System.out.println(timeUnit.toMicros(time));
        System.out.println(timeUnit.toNanos(time));

        System.out.println("48Hour is "+(timeUnit.convert(48, TimeUnit.HOURS))+timeUnit.name());
        System.out.println("-------------------");

    }
}
