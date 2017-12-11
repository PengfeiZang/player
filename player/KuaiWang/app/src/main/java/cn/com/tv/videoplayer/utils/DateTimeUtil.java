package cn.com.tv.videoplayer.utils;

import android.text.TextUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期时间工具类
 *
 * @author yanan.wang
 * @date 2013.4.17
 */
public class DateTimeUtil {

    public static final String dateFormatter = "yyyy-MM-dd";
    public static final String dateFormatterChinese = "yyyy/MM/dd";
    public static final String dateFormatterChineseNoYear = "MM/dd";
    public static final String dateFormatterNoSplitNoDay = "yyyyMM";
    public static final String dateFormatterNoSplit = "yyyyMMdd";
    public static final String dateTimeFormatter = "yyyy-MM-dd HH:mm:ss";
    public static final String dateTimeFormatterNoSecond = "yyyy-MM-dd HH:mm";
    public static final String dateTimeFormatterNoYearNoSecond = "MM-dd HH:mm";
    public static final String dateTimeFormatterChinese = "yyyy/MM/dd HH:mm:ss";
    public static final String dateTimeFormatterChineseNoYearNoSecond = "MM/dd HH:mm";
    public static final String dateTimeFormatterNoSplit = "yyyyMMddHHmmss";
    public static final String timeFormater = "HH:mm:ss";
    public static final String timeFormaterNoSecond = "HH:mm";
    public static final String hourFormater = "HH";
    public static final String minuteFormater = "mm";
    public static final String secondFormater = "ss";

    private DateTimeUtil() {

    }

    /**
     * 按指定格式取得当前日期字符串
     *
     * @param pattern
     * @return
     */
    public static final String getDateStringByPattern(String pattern) {
        Date date = new Date();
        return getDateStringByPattern(date, pattern);
    }


    /**
     * 按指定格式,将指定时间获取字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static final String getDateStringByPattern(long date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 按指定格式取得当前日期类型
     *
     * @param pattern
     * @return
     */
    public static final Date getCurrentDate(String pattern) {
        Date date = new Date();
        String dateStr = getDateStringByPattern(date, pattern);
        return getDateByPattern(dateStr, pattern);
    }

    /**
     * 取得当前日期
     *
     * @return
     */
    public static final Calendar getCurrentCalendar() {
        return Calendar.getInstance();
    }

    /**
     * 将字符串时间 转换 成long
     *
     * @param strDate
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static long getDateLongByPattern(String strDate, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(strDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static final String getDateStringByPattern(Date date, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        String str = sf.format(date);
        return str;
    }

    /**
     * 将时间戳按指定格式进行格式化
     *
     * @param strts
     * @param pattern
     * @return
     */
    public static final String getTimestampStringByPattern(String strts, String pattern) {
        Timestamp ts = Timestamp.valueOf(strts);
        return getDateStringByPattern(ts, pattern);
    }

    /**
     * 将时间戳按指定格式进行格式化
     *
     * @param ts
     * @param pattern
     * @return
     */
    public static final String getDateStringByPattern(Timestamp ts, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        String str = sf.format(ts);
        return str;
    }

    /**
     * 将时间戳转换为日期字符串
     *
     * @param ts
     * @return
     */
    public static final String getDateTimeString(Timestamp ts) {
        if (ts == null)
            return "";
        String str = ts.toString();
        if (str.length() >= 19)
            str = str.substring(0, 19);
        return str;
    }

    /**
     * 取得当前时间戳
     *
     * @return
     */
    public static final Timestamp getDateTime() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    /**
     * 取得当前时间戳字符串
     *
     * @return
     */
    public static final String getDateTimeString() {
        Timestamp ts = getDateTime();
        return getDateTimeString(ts);
    }

    /**
     * 将日期字符串转换为Date类型
     *
     * @param strDate
     * @param pattern
     * @return
     */
    public static final Date getDateByPattern(String strDate, String pattern) {
        if (strDate.equals("") || strDate == null) {
            return null;
        }
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static final Date getStepDate(Date date, int stepDays) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, stepDays);
        return cal.getTime();
    }

    public static final Date getStepSecond(Date date, int stepSeconds) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, stepSeconds);
        return cal.getTime();
    }

    public static final Date getStepMonth(Date date, int stepMonths) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, stepMonths);
        return cal.getTime();
    }

    public static final int getLeaveSec(long start, long end, int lenght) {
        int time = lenght - (int) ((end - start) / 1000);
        if (time > 0) {
            return time;
        } else {
            return 0;
        }
    }

    public static final int marginCalculations(Date start, Date end) {
        long startTime = start.getTime();
        long endTime = end.getTime();
        int val = (int) ((endTime - startTime) / 1000);
        return val;
    }

    public static final Date getTodayClock(int hour) {
        Date today = new Date();
        SimpleDateFormat sf = new SimpleDateFormat(dateFormatter);
        try {
            today = sf.parse(sf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(today.getTime() + hour * 60 * 60 * 1000);
    }

    public static final Date getTomorrowClock(int hour) {
        Date curr = getCurrentDate(dateFormatter);
        Date tomorrow = getStepDate(curr, 1);
        return new Date(tomorrow.getTime() + hour * 60 * 60 * 1000);
    }

    public static final Date getTomorrowClock(Date date, int hour) {
        Date tomorrow = getStepDate(date, 1);
        return new Date(tomorrow.getTime() + hour * 60 * 60 * 1000);
    }

    public static final int calculationDateDiff(Date start, Date end) {
        long diff = (end.getTime() - start.getTime()) / 1000;
        long days = diff / (24 * 3600);
        return (int) days;
    }

    /**
     * 计算过去几年
     *
     * @param start
     * @return
     */
    public static final int calculatePastYears(Date start) {
        if (start == null)
            return 0;
        Calendar cal = Calendar.getInstance();

        int now = cal.get(Calendar.YEAR);

        cal.setTime(start);
        int past = cal.get(Calendar.YEAR);
        return now - past;
    }

    /**
     * 获取当天最晚时间
     *
     * @return
     */
    public static final Date getTodayLatestTime() {
        Date today = new Date();
        SimpleDateFormat sf = new SimpleDateFormat(dateFormatter);
        try {
            today = sf.parse(sf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(today.getTime() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
    }

    /**
     * 获取时间差字符串（xxx分钟之前）
     *
     * @param start
     * @param pattern 设置返回的时间格式（如果超过一天会显示日期，这里设置这个日期格式）默认为yyyy-MM-dd
     * @return
     */
    public static final String getLevelTimeString(Date start, String pattern) {
        if (pattern == null) {
            pattern = "yyyy-MM-dd HH:mm";
        }
        int level = marginCalculations(start, getDateTime());
        if (level / 60 == 0) {
            return level + "秒前";
        } else if (level / 60 < 60) {
            return level / 60 + "分钟前";
        } else if (level / 3600 < 24) {
            return level / 3600 + "小时前";
        } else {
            return getDateStringByPattern(start, pattern);
        }
    }

    /**
     * 将时分秒的字符格式，转换为，long类型的毫秒数
     *
     * @param timeStr
     * @param pattern "hh:mm:dd"
     * @return
     */
    public static long timeStr2Time(String timeStr, String pattern) {
        long time = 0;
        if (null != timeStr && !"".equals(timeStr)) {
            if (timeStr.length() == 8) { // hh:mm:dd
                String[] splitTimeStr = timeStr.split(":");
                int hour = Integer.parseInt(splitTimeStr[0]);
                int minute = Integer.parseInt(splitTimeStr[1]);
                int second = Integer.parseInt(splitTimeStr[2]);
                time = (hour * 3600 + minute * 60 + second) * 1000;
            }
        }
        return time;
    }

    public static final void main(String[] args) throws Exception {
//        Date last = getCurrentDate(dateFormatter);
//        Date now = getCurrentDate(dateFormatter);
//        System.out.println(last + ", " + now);
//        System.out.println(last.getTime() == now.getTime());
//    	  System.out.println(getDateByPattern("2013-3-3", dateTimeFormatter));

//    	String date = "2013/3/3 3:3:3";
//    	String date2 = "2013-3-3 3:3:3";
//    	SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormatterChinese);
//		System.out.println(sdf.parse(date));

//    	for(int i = 0 ; i < 10; i++) {
//    		Date date = new Date((new Date()).getTime() + i * 1000);
//    		System.out.println(date);
//    	}

//        long time = timeStr2Time("01:00:00", timeFormater);
//        System.out.println(time);

//        String hour = getDateStringByPattern(hourFormater);
//        System.out.print(hour);

        String dateStringByPattern = getDateStringByPattern(0, dateTimeFormatter);
        System.out.print(dateStringByPattern);
    }

    /**
     * 把时间字符串转换为 calendar
     *
     * @param time
     * @param pattern
     * @return
     */
    public static Calendar str2Calendar(String time, String pattern) {
        // "yyyy-MM-dd"
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date;
        Calendar calendar = Calendar.getInstance();// 存储服务时间的Calendar
        try {
            date = simpleDateFormat.parse(time);
            calendar.setTime(date);
            return calendar;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 以友好的方式显示时间
     *
     * @param sdate
     * @return
     */
    public static String friendly_time(String sdate) {
        if (TextUtils.isEmpty(sdate)) {
            return "";
        }

        Date time = null;

        if (TimeZoneUtil.isInEasternEightZones())
            time = getDateByPattern(sdate, dateTimeFormatter);
        else
            time = TimeZoneUtil.transformTime(getDateByPattern(sdate, dateTimeFormatter),
                    TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());

        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = getDateStringByPattern(cal.getTime(), dateTimeFormatter);
        String paramDate = getDateStringByPattern(time, dateTimeFormatter);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天 ";
        } else if (days > 2 && days < 31) {
            ftime = days + "天前";
        } else if (days >= 31 && days <= 2 * 31) {
            ftime = "一个月前";
        } else if (days > 2 * 31 && days <= 3 * 31) {
            ftime = "2个月前";
        } else if (days > 3 * 31 && days <= 4 * 31) {
            ftime = "3个月前";
        } else {
            ftime = getDateStringByPattern(time, dateTimeFormatter);
        }
        return ftime;
    }

    public static String friendly_time2(String sdate) {
        if (StringUtil.isEmpty(sdate))
            return "";

        String res = "";
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String currentData = getDateStringByPattern("MM-dd");
        int currentDay = StringUtil.toInt(currentData.substring(3));
        int currentMoth = StringUtil.toInt(currentData.substring(0, 2));

        int sMoth = StringUtil.toInt(sdate.substring(5, 7));
        int sDay = StringUtil.toInt(sdate.substring(8, 10));
        int sYear = StringUtil.toInt(sdate.substring(0, 4));
        Date dt = new Date(sYear, sMoth - 1, sDay - 1);

        if (sDay == currentDay && sMoth == currentMoth) {
            res = "今天 / " + weekDays[getWeekOfDate(new Date())];
        } else if (sDay == currentDay + 1 && sMoth == currentMoth) {
            res = "昨天 / " + weekDays[(getWeekOfDate(new Date()) + 6) % 7];
        } else {
            if (sMoth < 10) {
                res = "0";
            }
            res += sMoth + "/";
            if (sDay < 10) {
                res += "0";
            }
            res += sDay + " / " + weekDays[getWeekOfDate(dt)];
        }

        return res;
    }

    public static String friendly_time3(String sdate) {
        if (TextUtils.isEmpty(sdate)) {
            return "";
        }
        StringBuffer sBuffer = new StringBuffer();
        Date date = DateTimeUtil.getDateByPattern(sdate, DateTimeUtil.dateTimeFormatterNoSecond);
        String dateTimeFormatterNoYearNoSecond = DateTimeUtil.getDateStringByPattern(date, DateTimeUtil.dateTimeFormatterNoYearNoSecond);
        String[] split = dateTimeFormatterNoYearNoSecond.split(" ");
        String[] dates = split[0].split("-");
        sBuffer.append(dates[0] + "月" + dates[1] + "日").append(" " + split[1]);
        return sBuffer.toString();
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }

    /**
     * 判断给定字符串时间是否为今日
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = getDateByPattern(sdate, dateTimeFormatter);
        Date today = new Date();
        if (time != null) {
            String nowDate = getDateStringByPattern(today, dateFormatter);
            String timeDate = getDateStringByPattern(time, dateFormatter);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 返回long类型的今天的日期
     *
     * @return
     */
    public static long getToday() {
        Calendar cal = Calendar.getInstance();
        String curDate = getDateStringByPattern(cal.getTime(), dateFormatter);
        curDate = curDate.replace("-", "");
        return Long.parseLong(curDate);
    }

    public static String getCurTimeStr() {
        Calendar cal = Calendar.getInstance();
        String curDate = getDateStringByPattern(cal.getTime(), dateTimeFormatter);
        return curDate;
    }

    /***
     * 计算两个时间差，返回的是的秒s
     *
     * @param dete1
     * @param date2
     * @return
     */
    public static long calDateDifferent(String dete1, String date2) {

        long diff = 0;

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = getDateByPattern(dete1, dateTimeFormatter);
            d2 = getDateByPattern(date2, dateTimeFormatter);

            // 毫秒ms
            diff = d2.getTime() - d1.getTime();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return diff / 1000;
    }

    /**
     * 获取当前时间为每年第几周
     *
     * @return
     */
    public static int getWeekOfYear() {
        return getWeekOfYear(new Date());
    }

    /**
     * 获取当前时间为每年第几周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        int week = c.get(Calendar.WEEK_OF_YEAR) - 1;
        week = week == 0 ? 52 : week;
        return week > 0 ? week : 1;
    }

    public static int[] getCurrentDate() {

        int[] dateBundle = new int[3];
        String[] temp = getDateStringByPattern("yyyy-MM-dd").split("-");

        for (int i = 0; i < 3; i++) {
            try {
                dateBundle[i] = Integer.parseInt(temp[i]);
            } catch (Exception e) {
                dateBundle[i] = 0;
            }
        }
        return dateBundle;
    }

    public static String geTime() {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        Date date = new Date();
        return format.format(date);
    }

    public static String geTime(long millions) {
        SimpleDateFormat format = new SimpleDateFormat(dateTimeFormatter);
        Date date = new Date(millions);
        return format.format(date);
    }

    public static String geTime(long millions, String p) {
        SimpleDateFormat format = new SimpleDateFormat(p);
        Date date = new Date(millions);
        return format.format(date);
    }

    public static long stringToLong(String strTime, String formatType)
            throws ParseException {

        String s = strTime;
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);//"yyyy-MM-dd HH:mm:ss"
        Date date = sdf.parse(s);

        return date.getTime();
    }

    public static String friendly_time(long millions, String sdate) {
        Date time = null;

        if (TimeZoneUtil.isInEasternEightZones())
            time = getDateByPattern(sdate, dateTimeFormatter);
        else
            time = TimeZoneUtil.transformTime(getDateByPattern(sdate, dateTimeFormatter),
                    TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());

        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = getDateStringByPattern(cal.getTime(), dateTimeFormatter);
        String paramDate = getDateStringByPattern(time, dateTimeFormatter);
        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            ftime = geTime(millions, timeFormaterNoSecond);
        } else if (days == 1) {
            ftime = "昨天" + " " + geTime(millions, timeFormaterNoSecond);
        } else {
            ftime = friendly_time3(sdate);
        }
        return ftime;
    }

    public static String friendly_time_message_list(long millions, String sdate) {
        Date time = null;

        if (TimeZoneUtil.isInEasternEightZones())
            time = getDateByPattern(sdate, dateTimeFormatter);
        else
            time = TimeZoneUtil.transformTime(getDateByPattern(sdate, dateTimeFormatter),
                    TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());

        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = getDateStringByPattern(cal.getTime(), dateTimeFormatter);
        String paramDate = getDateStringByPattern(time, dateTimeFormatter);
        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            ftime = geTime(millions, timeFormaterNoSecond);
        } else if (days == 1) {
            ftime = "昨天" + " " + geTime(millions, timeFormaterNoSecond);
        } else {
            ftime = friendly_time5(sdate);
        }
        return ftime;
    }

    public static String live_play_list_time(long millions, String sdate) {
        Date time = null;

        if (TimeZoneUtil.isInEasternEightZones())
            time = getDateByPattern(sdate, dateTimeFormatter);
        else
            time = TimeZoneUtil.transformTime(getDateByPattern(sdate, dateTimeFormatter),
                    TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());

        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = getDateStringByPattern(cal.getTime(), dateTimeFormatter);
        String paramDate = getDateStringByPattern(time, dateTimeFormatter);
        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 1) {
            ftime = "今天";
        }
//        else if (days == 1) {
//            ftime = "昨天";
//        }
        else {
            ftime = friendly_time5(sdate);
        }
        return ftime;
    }

    public static String friendly_time5(String sdate) {
        StringBuffer sBuffer = new StringBuffer();
        Date date = DateTimeUtil.getDateByPattern(sdate, DateTimeUtil.dateTimeFormatter);
        String dateTimeFormatterNoYearNoSecond = DateTimeUtil.getDateStringByPattern(date, DateTimeUtil.dateTimeFormatterNoYearNoSecond);
        String[] split = dateTimeFormatterNoYearNoSecond.split(" ");
        String[] dates = split[0].split("-");
        sBuffer.append(dates[0] + "月" + dates[1] + "日");
        return sBuffer.toString();
    }


}