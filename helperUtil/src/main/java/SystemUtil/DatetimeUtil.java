package SystemUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 */
public class DatetimeUtil {

    //The default time format 1 :  year - month - day hours: minutes: seconds
    private static final String DEFAULT_FORMAT1="yyyy-MM-dd HH:mm:ss";
    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    //The default time format 2 : year - month - day
    private static final String DEFAULT_FORMAT2="yyyy-MM-dd";
    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    //The default time format 3:00: minutes: seconds
    private static final String DEFAULT_FORMAT3="HH:mm:ss";

    //The number of milliseconds of the day
    public static final long DAY_TIME_MILLIS = 24 * 60 * 60 * 1000;

    /**
     * Get the current time string
     * @param format Formatted string, such as "yyyy-MM-dd HH: mm: ss"
     * @return String type of the current date and time
     */
    public static String getCurrentDatetime(String format){
        String currentDateTime=null;
        if (null==format||"".equals(format)){
            return null;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            Calendar calendar = new GregorianCalendar();
            currentDateTime = simpleDateFormat.format(calendar.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        return currentDateTime;
    }

    /**
     * Time to get the current time format
     * @return Year - month - day hour: minutes: seconds format, current time
     */
    public static String getCurrentDatetime(){
        return getCurrentDatetime(DEFAULT_FORMAT1);
    }

    /**
     * The date format for the specified time
     * @param date Date type specified time
     * @param format Format string
     * @return After formatting time
     */
    public static String getDate(Date date,String format){
        String currentDate=null;
        try {
            SimpleDateFormat formatter=new SimpleDateFormat(format);
            currentDate=formatter.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }

        return currentDate;

    }

    /**
     * Formatting time for the specified number of milliseconds, specified format
     * @param time Milliseconds
     * @param format Format string
     * @return Time formatted
     */
    public static String getDate(long time,String format){
        Date date=new Date(time);
        return getDate(date, format);
    }

    /**
     * Date for the specified type of formatting time
     * @param date Time Date
     * @return Time formatted
     */
    public static String getDate(Date date){
        return getDate(date, DEFAULT_FORMAT2);
    }

    /**
     * Get formatted time specified number of milliseconds
     * @param time Milliseconds
     * @return Time formatted
     */
    public static String getDate(long time){
        return getDate(time, DEFAULT_FORMAT2);
    }

    /**
     * Get the current time period specified format string formatting
     * @param format Format string
     * @return Time formatted
     */
    public static String getCurrentDate(String format){
        return getDate(System.currentTimeMillis(), format);
    }
    /**
     * Time to get the current time format
     * @return Time formatted
     */
    public static String getCurrentDate(){
        return getDate(System.currentTimeMillis(), DEFAULT_FORMAT2);
    }

    /**
     * Date type for the specified number of milliseconds
     * @param date Specified Date
     * @return Date type specified number of milliseconds
     */
    public static long getTimeMillis(Date date){
        return date.getTime();
    }

    /**
     * Get the number of milliseconds the current time
     * @return The number of milliseconds the current time
     */
    public static long getCurrentDayTimeMillis(){
        return System.currentTimeMillis();
    }

    /**
     * Get the current time of the day TimeMillis
     * @return After the current time of day TimeMillis
     */
    public static long getNextDayTimeMillis(){
        return getCurrentDayTimeMillis()+DAY_TIME_MILLIS;
    }

    /**
     * Get the current time the day before TimeMillis
     * @return Current time in the previous day's TimeMillis
     */
    public static long getPreDayTimeMillis(){
        return getCurrentDayTimeMillis()-DAY_TIME_MILLIS;
    }

    /**
     *  According String input such as 2013-03-07, returns Thursday
     * @param strDate
     * @return
     */
    public static String getDayOfweek(String strDate) {
        Calendar calendar = Calendar.getInstance();
        if (strDate == null) {
            return null;
        }

        String[] dateSlipt = strDate.split("-");
        if (dateSlipt.length != 3 | dateSlipt.length == 0) {
            return null;
        }

        calendar.set(Calendar.YEAR, Integer.valueOf(dateSlipt[0]));
        calendar.set(Calendar.MONTH, Integer.valueOf(dateSlipt[1]) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dateSlipt[2]));

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return getSimpleDateOfWeek(dayOfWeek);

    }
    /**
     * Based on the input i, i to calendar week index, returns the week
     * @param i calendar week index
     * @return Day of the week
     */

    public static String getDateOfWeek(int i){
        String date=null;
        switch (i){
            case Calendar.SUNDAY:
                date = "SUNDAY";
                 break;
            case Calendar.MONDAY:
                date = "MONDAY";
                break;
            case Calendar.TUESDAY:
                date = "TUESDAY";
                break;
            case Calendar.WEDNESDAY:
                date = "WEDNESDAY";
                break;
            case Calendar.THURSDAY:
                date = "THURSDAY";
                break;
            case Calendar.FRIDAY:
                date = "FRIDAY";
                break;
            case Calendar.SATURDAY:
                date = "SATURDAY";
                break;
            default:
                break;
        }
        return  date;
    }

    /**
     * Based on the input i, i to calendar week index, returns the week
     * @param i calendar week index
     * @return Of the week
     */
    public static String getSimpleDateOfWeek(int i){
        String date=null;
        switch (i){
            case Calendar.SUNDAY:
                date = "SUNDAY";
                break;
            case Calendar.MONDAY:
                date = "MONDAY";
                break;
            case Calendar.TUESDAY:
                date = "TUESDAY";
                break;
            case Calendar.WEDNESDAY:
                date = "WEDNESDAY";
                break;
            case Calendar.THURSDAY:
                date = "THURSDAY";
                break;
            case Calendar.FRIDAY:
                date = "FRIDAY";
                break;
            case Calendar.SATURDAY:
                date = "SATURDAY";
                break;
            default:
                break;
        }
        return  date;
    }

    /**
     * The formatted time string into milliseconds
     * @param day The formatted time
     * @param format Format string
     * @return millisecond
     */
    public static long convertMillisecond(String day, String format) {
        if (day == null || format == null)
            return 0;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            Date dt = formatter.parse(day);
            return dt.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get the number of days between two dates
     * @param sdate1 Date a
     * @param sdate2 Date two
     * @return The number of days
     */
    public static int getDateInterval(String sdate1, String sdate2) {
        Date date1 = null;
        Date date2 = null;
        long betweenDays=0;

        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sdate1);
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(sdate2);

            long beginTime = date1.getTime();
            long endTime = date2.getTime();
            betweenDays = (long) ((endTime - beginTime) / (1000 * 60 * 60 * 24));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (int) betweenDays;
    }

    /**
     * Time Comparison
     * @param format Format string
     * @param time1 Time 1
     * @param time2 Time 2
     * @return -1 is returned earlier than time1 time2, time1 and time2 same return 0, time1 time2 later than 1 return
     */
    public static int compareTime(String format, String time1, String time2) {
        if (format == null || time1 == null || time2 == null)
            return 0;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
            c1.setTime(formatter.parse(time1));
            c2.setTime(formatter.parse(time2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return c1.compareTo(c2);
    }

    /**
     * Convert String --> Date
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Check String is Date or not
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }
}
