package app.kits;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jfinal.ext.kit.DateKit;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * .
 * </p>
 *
 * @author Jerry Ou
 * @version 1.0 2014-07-15 11:01
 * @since JDK 1.6
 */
public final class CalendarKit {

    public static final DateTimeFormatter DATE_FORMATTER_YYYYMMDD_DOT = DateTimeFormat.forPattern(DateKit.dateFormat);
    public static final DateTimeFormatter DATE_FORMATTER_YYYYMMDDHHMMSS_DOT = DateTimeFormat.forPattern(DateKit.timeFormat);

    /**
     * 把string类型的日期转化为相应的格式
     *
     * @param date      字符串日期
     * @param formatNum 1 yyyyMMdd
     *                  2 yyyy/MM/dd
     *                  3 yyyy-MM-dd
     *                  4 yyyy-MM-dd HH:mm:ss
     *                  5 yyyyMMddHHmmss
     *                  6 yyyy-MM-dd HH:mm:ss.sss
     *                  7 HH:mm
     *                  8 yyyy年MM月dd日
     *                  9 yyyyMMddHHmmssSSS
     *                  10 yyyy年MM月dd日 HH:mm
     *                  11 yyyy-MM-dd HH
     *                  12 HH:mm:ss
     *                  14 yyyy-MM-ddTHH:mm:ss+08:00
     *                  15 yyyy-MM-dd HH:mm
     *                  16 yyyyMM
     *                  17 yyyy年MM月dd日hh时mm分
     * @return 字符串日期
     */
    public static String dateToString(Date date, int formatNum) {
        String str = null;
        if (date != null) {
            // return parseString(date, formatNum);
            str = dateFormat(formatNum).format(date);
        }
        return str;
    }

    /**
     * 把string类型的日期转化为相应的格式
     *
     * @param str      字符串日期
     * @param formatNum 1 yyyyMMdd
     *                  2 yyyy/MM/dd
     *                  3 yyyy-MM-dd
     *                  4 yyyy-MM-dd HH:mm:ss
     *                  5 yyyyMMddHHmmss
     *                  6 yyyy-MM-dd HH:mm:ss.sss
     *                  7 HH:mm
     *                  8 yyyy年MM月dd日
     *                  9 yyyyMMddHHmmssSSS
     *                  10 yyyy年MM月dd日 HH:mm
     *                  11 yyyy-MM-dd HH
     *                  12 HH:mm:ss
     *                  14 yyyy-MM-ddTHH:mm:ss+08:00
     *                  15 yyyy-MM-dd HH:mm
     *                  16 yyyyMM
     *                  17 yyyy年MM月dd日hh时mm分
     * @return 日期
     */
    public static Date stringToDate(String str, int formatNum) {
        Date date = null;
        if (!Strings.isNullOrEmpty(str)) {
            // return parseString(date, formatNum);
            try {
                date = dateFormat(formatNum).parse(str);
            } catch (ParseException e) {
                //
            }
        }
        return date;
    }

    /**
     * 实例SimpleDateFormat
     *
     * @param formatNum 1 yyyyMMdd
     *                  2 yyyy/MM/dd
     *                  3 yyyy-MM-dd
     *                  4 yyyy-MM-dd HH:mm:ss
     *                  5 yyyyMMddHHmmss
     *                  6 yyyy-MM-dd HH:mm:ss.sss
     *                  7 HH:mm
     *                  8 yyyy年MM月dd日
     *                  9 yyyyMMddHHmmssSSS
     *                  10 yyyy年MM月dd日 HH:mm
     *                  11 yyyy-MM-dd HH
     *                  12 HH:mm:ss
     *                  17 yyyy年MM月dd日hh时mm分
     * @return 格式日期
     */
    private static SimpleDateFormat dateFormat(int formatNum) {
        SimpleDateFormat dateFormatter = null;
        switch (formatNum) {
            case 1:
                dateFormatter = new SimpleDateFormat("yyyyMMdd");
                break;
            case 2:
                dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
                break;
            case 3:
                dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                break;
            case 4:
                dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
            case 5:
                dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
                break;
            case 6:
                dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
                break;
            case 7:
                dateFormatter = new SimpleDateFormat("HH:mm");
                break;
            case 8:
                dateFormatter = new SimpleDateFormat("yyyy年MM月dd日");
                break;
            case 9:
                dateFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                break;
            case 10:
                dateFormatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                break;
            case 11:
                dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH");
                break;
            case 12:
                dateFormatter = new SimpleDateFormat("HH:mm:ss");
                break;
            case 14:
                dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+08:00");
                break;
            case 15:
                dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                break;
            case 16:
                dateFormatter = new SimpleDateFormat("yyyyMM");
                break;
            case 17:
                dateFormatter = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
                break;
            case 18:
                dateFormatter = new SimpleDateFormat("yyyy-MM-dd 08:00:00");
                break;
        }
        return dateFormatter;
    }


    /**
     * 给定日期到当前日期的年数
     *
     * @param date 日期
     * @return 年数
     */
    public static final int yearsForm(Date date) {
        if (date == null) {
            return 0;
        }
        DateTime dateTime = new DateTime(date);
        DateTime now = DateTime.now();

        int y1 = now.getYear();
        int y2 = dateTime.getYear();

        return (y1 - y2) + 1;
    }

    /**
     * 获取 {year}-{month} 开始时间 至 现在的结束时间
     *
     * @param year 年
     * @param month 月
     * @return 开始结束日期数组
     */
    public static Date[] monthBeginAndEnd(int year, int month) {
        String s = year + "-" + (month < 10 ? "0" + month : month) + "-01 00:00:00";
        Date start = DateTime.parse(s, DATE_FORMATTER_YYYYMMDDHHMMSS_DOT).toDate();
        Date end = new Date();
        return new Date[] {start, end};
    }

    /**
     * 生成 {date} 到前 bal+1 的所有时间
     *
     * @param date 日期字符串
     * @param bal 时间
     * @return 时间字符串
     */
    public static List<String> generate(String date, int bal) {
        List<String> list = Lists.newArrayList();
        DateTime dateTime = DateTime.parse(date, DATE_FORMATTER_YYYYMMDD_DOT);
        for (int i = bal; i >= 0; i--) {
            DateTime bef = dateTime.plusDays((0 - i));
            String s = bef.toString(DATE_FORMATTER_YYYYMMDD_DOT);
            list.add(s);
        }
        return list;
    }

    /**
     * 获取当月第一天
     *
     * @return 当月第一天
     */
    public static String getFirstDayOfMonth() {
        DateTime dateTime = DateTime.now().dayOfMonth().withMinimumValue();;
        return dateToString(dateTime.toDate(), 3);
    }

    /**
     * 获取当月最后一天
     *
     * @return 当月最后一天
     */
    public static String getLastDayOfMonth() {
        DateTime dateTime = DateTime.now().dayOfMonth().withMaximumValue();;
        return dateToString(dateTime.toDate(), 3);
    }

    /**
     * 获取 {date} 前 {days} 时间
     *
     * @param date 指定日期
     * @param days 前多少天
     * @return 计算得出日期
     */
    public static Date days(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(0-days).toDate();
    }

    /**
     * 返回开始时间和结束时间的时间区间，包含结束时间
     *  如：start --> 2014-05-01, 2014-05-10
     *  得到 :
     *  [
     *      2014-05-01,2014-05-02,2014-05-03,2014-05-04,2014-05-05,
     *      2014-05-06,2014-05-07,2014-05-08,2014-05-09,2014-05-10
     *  ]
     *
     *
     * @param start 开始日期
     * @param end 结束日期
     * @return 区间列表
     */
    public static List<String> convertToDateExtent(Date start, Date end) {
        List<String> list = Lists.newArrayList();

        if (start.equals(end)) {
            list.add(new DateTime(start).toString("yyyy-MM-dd"));
        } else {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            DateTime s = new DateTime(start);
            DateTime e = new DateTime(end);
            int len = (int) dayDiff(s.toDate(), e.toDate());
            len = len + 1;
            if (len > 0) {
                DateTime date = s;
                for (int i=0; i<len;i++) {
                    String r = date.toString(formatter);
                    list.add(r);
                    date = date.plusDays(1);
                }
            }
        }
        return list;
    }

    /**
     * 比较两个日期的相差天数,如果开始日期比结束日期早，则返回正数，否则返回负数。 0天返回0、小于等于一天返回1、大于一天返回2、依次类推
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 如果开始日期比结束日期早，则返回正数，否则返回负数。 0天返回0、小于等于一天返回1、大于一天返回2、依次类推
     */
    public static long dayDiff(Date start, Date end) {
        long day = compareDate(start, end);
        if (day == 0) {
            return 0;
        }
        if (day > 0) {
            return (day - 1) / 1000 / 60 / 60 / 24 + 1;
        } else {
            return (day + 1) / 1000 / 60 / 60 / 24 - 1;
        }
    }

    /**
     * 比较两个日期的相差毫秒数,如果开始日期比结束日期早，则返回正数，否则返回负数。
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 如果开始日期比结束日期早，则返回正数，否则返回负数
     */
    public static long compareDate(Date start, Date end) {
        Calendar starts = Calendar.getInstance();
        Calendar ends = Calendar.getInstance();
        starts.setTime(start);
        ends.setTime(end);
        return ends.getTime().getTime() - starts.getTime().getTime();
    }

    /**
     * 计算 {start} {end} 之间毫秒数
     *
     * @param start 开始时间
     * @param end 结束时间
     * @return 毫秒数
     */
    public static long balance(Date start, Date end) {
        long s = start.getTime();
        long e = end.getTime();
        return (e - s);
    }
}
