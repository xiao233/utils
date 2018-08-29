package xiao.utils.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import xiao.utils.check.ParamCheckUtils;

/**
 * 对时间的处理工具类
 * @author Administrator
 *
 */
public class TimeUtils {
	/**
	 * 时间格式，年月日
	 */
	public static String FORMAT_DATE = "yyyy-MM-dd"; 
	/**
	 * 时分秒,24制
	 */
	public static String FORMAT_TIME24 = "HH:mm:ss"; 
	/**
	 * 时分秒，12制
	 */
	public static String FORMAT_TIME12 = "hh:mm:ss"; 
	/**
	 * 年月日，时分秒12
	 */
	public static String FORMAT_DATE_TIME12 = "yyyy-MM-dd hh:mm:ss"; 
	/**
	 * 年月日，时分秒24
	 */
	public static String FORMAT_DATE_TIME24 = "yyyy-MM-dd HH:mm:ss"; 
	
	public static Long SECOND_TIME = 1000*1L; //秒
	
	public static Long MINUTE_TIME = 60*SECOND_TIME; //分
	
	public static Long HOUR_TIME = 60*MINUTE_TIME; //时
	
	public static Long DAY_TIME = 24*HOUR_TIME; //天
	
	public static Long WEEK_TIME = 7*DAY_TIME; //周
	/**
	 * 秒
	 */
	public static int SECOND_WAY = 0 ;//秒
	/**
	 * 分
	 */
	public static int MINUTE_WAY = 1; //分
	/**
	 * 时
	 */
	public static int HOUR_WAY = 2; //时
	/**
	 * 天
	 */
	public static int DAY_WAY = 3; //天 
	/**
	 * 周
	 */
	public static int WEEK_WAY = 4; //周
	/**
	 * 月
	 */
	public static int MONTH_WAY = 5; //月
	/**
	 * 年
	 */
	public static int YEAR_WAY = 6; //年
	
	
	private static SimpleDateFormat dateFormat ;
	
	/**
	 * 以固定格式显示时间
	 * @param date 时间
	 * @param format 时间格式(可从本类获取，也可自定义)
	 * @return
	 */
	public static String getTimeByFormat(Date date,String format) {
		try {
			ParamCheckUtils.paramIsNull(date, "时间为null");
			ParamCheckUtils.paramIsNull(format, "时间格式为null");
		}catch(IllegalArgumentException e) {
			format = FORMAT_DATE_TIME12;
			System.out.println("warning : "+e.getMessage());
			System.out.println("warning : 启用默认时间格式--"+format);
		}catch(Exception e) {
			e.printStackTrace();
		}
		dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	/**
	 * 将string类型转换为Date对象
	 * @param time 时间
	 * @param format 格式
	 * @return
	 */
	public static Date changeToDate(String time,String format) {
		try {		
			ParamCheckUtils.paramIsNull(time, "时间为null");
			ParamCheckUtils.paramIsNull(format, "时间格式为null");
		}catch(Exception e) {
			System.out.println("error : "+e.getMessage());
			return null;
		}
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(time);
		} catch (Exception e) {
			System.out.println("error : 时间不匹配时间格式");
			System.out.println("system error ---- " + e.getMessage());
		}
		return date;
	}
	/**
	 * 获取两个时间的毫秒数
	 * @param begin 起始时间
	 * @param end 截止时间
	 */
	public static Long getMillionTimeBetween(Date begin,Date end) {
		try {
			ParamCheckUtils.paramIsNull(begin, "时间为null");
			ParamCheckUtils.paramIsNull(end, "时间为null");
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			e.printStackTrace();
		}
		dateFormat = new SimpleDateFormat();
		Long time1 = null;
		Long time2 = null;

		time1 = begin.getTime();
		time2 = end.getTime();

		return time2 - time1;
	}
	/**
	 * 获取两时间的秒数
	 * @param begin 开始时间
	 * @param end 截止时间
	 * @return
	 */
	public static Long getSecondsTimeBetween(Date begin,Date end) {
		Long time = getMillionTimeBetween(begin, end);
		return time==null?null:time/SECOND_TIME;
	}
	
	/**
	 * 获取两时间的分数
	 * @param begin 开始时间
	 * @param end 截止时间
	 * @return
	 */
	public static double getMinutesTimeBetween(Date begin,Date end) {
		Long time = getMillionTimeBetween(begin, end);
		double time1 = time==null?0:time;
		return time1/MINUTE_TIME;
	}
	/**
	 * 获取两时间的小时数
	 * @param begin 开始时间
	 * @param end 截止时间
	 * @return
	 */
	public static double getHoursTimeBetween(Date begin,Date end) {
		Long time = getMillionTimeBetween(begin, end);
		double time1 = time==null?0:time;
		return time1/HOUR_TIME;
	}
	/**
	 * 获取两时间的天数
	 * @param begin 开始时间
	 * @param end 截止时间
	 * @return
	 */
	public static double getDaysTimeBetween(Date begin,Date end) {
		Long time = getMillionTimeBetween(begin, end);
		double time1 = time==null?0:time;
		return time1/DAY_TIME;
	}
	/**
	 * 获取两时间的周数
	 * @param begin 开始时间
	 * @param end 截止时间
	 * @return
	 */
	public static double getWeeksTimeBetween(Date begin,Date end) {
		Long time = getMillionTimeBetween(begin, end);
		double time1 = time==null?0:time;
		return time1/WEEK_TIME;
	}
	/**
	 * 获取两时间的月数
	 * @param begin 开始时间
	 * @param end 截止时间
	 * @return
	 */
	public static int getMonthsTimeBetween(Date begin,Date end) {
		try {
			ParamCheckUtils.paramIsNull(begin, "时间为null");
			ParamCheckUtils.paramIsNull(end, "时间为null");
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			e.printStackTrace();
		}
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(begin);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		int year = calEnd.get(Calendar.YEAR)-calBegin.get(Calendar.YEAR);
		int month = 12*year+ calEnd.get(Calendar.MONTH)-calBegin.get(Calendar.MONTH);
		return month;
	}
	/**
	 * 获取两时间的年数
	 * @param begin 开始时间
	 * @param end 截止时间
	 * @return
	 */
	public static int getYearsTimeBetween(Date begin,Date end) {
		try {
			ParamCheckUtils.paramIsNull(begin, "时间为null");
			ParamCheckUtils.paramIsNull(end, "时间为null");
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			e.printStackTrace();
		}
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(begin);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		int year = calEnd.get(Calendar.YEAR)-calBegin.get(Calendar.YEAR);
		return year;
	}
	/**
	 * 计算给定时间之前或之后的日期
	 * @param oldDate 原时间
	 * @param time 多少
	 * @param way 计量单位（秒0、分1、时2、天3、周4、月5、年6，参见本类以_WAY结尾的变量）
	 * @return
	 */
	public static Date getDateAfterTime(Date oldDate,int time,int way) {
		try {
			ParamCheckUtils.paramIsNull(oldDate, "时间为null");
		}catch(IllegalArgumentException e) {
			System.out.println("error : "+e.getMessage());
			return null;
		}
		if(way<0) {
			way = 0;
		}
		
		if(way>6) {
			way = 6;
		}
		Long timeMillions = oldDate.getTime();
		Long timeAdd = 0L;
		
		if(way==5||way==6) {
			return timeAdd(oldDate,time,way);
		}else {
			timeAdd = timeAdd(time,way);
			return new Date(timeMillions+timeAdd);
		}
		
	}
	
	/**
	 * 原时间多少时间之后的时间
	 * @param oldDate 原时间
	 * @param time 多少
	 * @param way 单位（月、年）
	 * @return
	 */
	private static Date timeAdd(Date oldDate, int time, int way) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(oldDate);
		if(way == MONTH_WAY) {
			cal.set(Calendar.MONTH, time+cal.get(Calendar.MONTH));
		}else {
			cal.set(Calendar.YEAR, time+cal.get(Calendar.YEAR));
		}
		return cal.getTime();
	}
	/**
	 * 计算相应的毫秒数
	 * @param time 时间量
	 * @param way 单位(秒、分、时、天、周)
	 * @return
	 */
	public static Long timeAdd(int time, int way) {
		Long timeAdd = 0L;
		if(way == SECOND_WAY) {
			timeAdd = time*SECOND_TIME;
		}else if(way == MINUTE_WAY) {
			timeAdd = time*MINUTE_TIME;
		}else if(way == HOUR_WAY) {
			timeAdd = time*HOUR_TIME;
		}else if(way == DAY_WAY) {
			timeAdd = time*DAY_TIME;
		}else if(way == WEEK_WAY) {
			timeAdd = time*WEEK_TIME;
		}
		return timeAdd;
	}
}
