package date;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/9/20
 */

public class Java7Date {
    public static void main(String[] args) {
        printCalenderInfo();
        setCalender();
    }

    public static void printCalenderInfo(){
        Calendar calendar = new GregorianCalendar();
        System.out.println("Year= "+calendar.get(Calendar.YEAR));
        System.out.println("month= "+calendar.get(Calendar.MONTH));
        System.out.println("dayOfMonth= "+calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("dayOfWeek= "+calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("weekOfYear= "+calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("weekOfMonth= "+calendar.get(Calendar.WEEK_OF_MONTH));

        System.out.println("hour= "+calendar.get(Calendar.HOUR));
        System.out.println("hourOfDay= "+calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("minute= "+calendar.get(Calendar.MINUTE));
        System.out.println("second= "+calendar.get(Calendar.SECOND));
        System.out.println("millisecond= "+calendar.get(Calendar.MILLISECOND));
    }

    public static void setCalender(){
        Calendar calendar = new GregorianCalendar();
        //set date to last day of 2009
        calendar.set(Calendar.YEAR, 2009);
        calendar.set(Calendar.MONTH, 11); // 11 = december
        calendar.set(Calendar.DAY_OF_MONTH, 31); // new years eve
        //add one day
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        //date is now jan. 1st 2010
        System.out.println("Year= "+calendar.get(Calendar.YEAR)); // now 2010
        System.out.println("month= "+calendar.get(Calendar.MONTH));  // now 0 (Jan = 0)
        System.out.println("dayOfMonth= "+calendar.get(Calendar.DAY_OF_MONTH)); // now 1
    }
}
