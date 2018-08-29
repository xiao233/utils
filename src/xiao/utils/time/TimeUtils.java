package xiao.utils.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import xiao.utils.check.ParamCheckUtils;

/**
 * ��ʱ��Ĵ�������
 * @author Administrator
 *
 */
public class TimeUtils {
	/**
	 * ʱ���ʽ��������
	 */
	public static String FORMAT_DATE = "yyyy-MM-dd"; 
	/**
	 * ʱ����,24��
	 */
	public static String FORMAT_TIME24 = "HH:mm:ss"; 
	/**
	 * ʱ���룬12��
	 */
	public static String FORMAT_TIME12 = "hh:mm:ss"; 
	/**
	 * �����գ�ʱ����12
	 */
	public static String FORMAT_DATE_TIME12 = "yyyy-MM-dd hh:mm:ss"; 
	/**
	 * �����գ�ʱ����24
	 */
	public static String FORMAT_DATE_TIME24 = "yyyy-MM-dd HH:mm:ss"; 
	
	public static Long SECOND_TIME = 1000*1L; //��
	
	public static Long MINUTE_TIME = 60*SECOND_TIME; //��
	
	public static Long HOUR_TIME = 60*MINUTE_TIME; //ʱ
	
	public static Long DAY_TIME = 24*HOUR_TIME; //��
	
	public static Long WEEK_TIME = 7*DAY_TIME; //��
	/**
	 * ��
	 */
	public static int SECOND_WAY = 0 ;//��
	/**
	 * ��
	 */
	public static int MINUTE_WAY = 1; //��
	/**
	 * ʱ
	 */
	public static int HOUR_WAY = 2; //ʱ
	/**
	 * ��
	 */
	public static int DAY_WAY = 3; //�� 
	/**
	 * ��
	 */
	public static int WEEK_WAY = 4; //��
	/**
	 * ��
	 */
	public static int MONTH_WAY = 5; //��
	/**
	 * ��
	 */
	public static int YEAR_WAY = 6; //��
	
	
	private static SimpleDateFormat dateFormat ;
	
	/**
	 * �Թ̶���ʽ��ʾʱ��
	 * @param date ʱ��
	 * @param format ʱ���ʽ(�ɴӱ����ȡ��Ҳ���Զ���)
	 * @return
	 */
	public static String getTimeByFormat(Date date,String format) {
		try {
			ParamCheckUtils.paramIsNull(date, "ʱ��Ϊnull");
			ParamCheckUtils.paramIsNull(format, "ʱ���ʽΪnull");
		}catch(IllegalArgumentException e) {
			format = FORMAT_DATE_TIME12;
			System.out.println("warning : "+e.getMessage());
			System.out.println("warning : ����Ĭ��ʱ���ʽ--"+format);
		}catch(Exception e) {
			e.printStackTrace();
		}
		dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	/**
	 * ��string����ת��ΪDate����
	 * @param time ʱ��
	 * @param format ��ʽ
	 * @return
	 */
	public static Date changeToDate(String time,String format) {
		try {		
			ParamCheckUtils.paramIsNull(time, "ʱ��Ϊnull");
			ParamCheckUtils.paramIsNull(format, "ʱ���ʽΪnull");
		}catch(Exception e) {
			System.out.println("error : "+e.getMessage());
			return null;
		}
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(time);
		} catch (Exception e) {
			System.out.println("error : ʱ�䲻ƥ��ʱ���ʽ");
			System.out.println("system error ---- " + e.getMessage());
		}
		return date;
	}
	/**
	 * ��ȡ����ʱ��ĺ�����
	 * @param begin ��ʼʱ��
	 * @param end ��ֹʱ��
	 */
	public static Long getMillionTimeBetween(Date begin,Date end) {
		try {
			ParamCheckUtils.paramIsNull(begin, "ʱ��Ϊnull");
			ParamCheckUtils.paramIsNull(end, "ʱ��Ϊnull");
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
	 * ��ȡ��ʱ�������
	 * @param begin ��ʼʱ��
	 * @param end ��ֹʱ��
	 * @return
	 */
	public static Long getSecondsTimeBetween(Date begin,Date end) {
		Long time = getMillionTimeBetween(begin, end);
		return time==null?null:time/SECOND_TIME;
	}
	
	/**
	 * ��ȡ��ʱ��ķ���
	 * @param begin ��ʼʱ��
	 * @param end ��ֹʱ��
	 * @return
	 */
	public static double getMinutesTimeBetween(Date begin,Date end) {
		Long time = getMillionTimeBetween(begin, end);
		double time1 = time==null?0:time;
		return time1/MINUTE_TIME;
	}
	/**
	 * ��ȡ��ʱ���Сʱ��
	 * @param begin ��ʼʱ��
	 * @param end ��ֹʱ��
	 * @return
	 */
	public static double getHoursTimeBetween(Date begin,Date end) {
		Long time = getMillionTimeBetween(begin, end);
		double time1 = time==null?0:time;
		return time1/HOUR_TIME;
	}
	/**
	 * ��ȡ��ʱ�������
	 * @param begin ��ʼʱ��
	 * @param end ��ֹʱ��
	 * @return
	 */
	public static double getDaysTimeBetween(Date begin,Date end) {
		Long time = getMillionTimeBetween(begin, end);
		double time1 = time==null?0:time;
		return time1/DAY_TIME;
	}
	/**
	 * ��ȡ��ʱ�������
	 * @param begin ��ʼʱ��
	 * @param end ��ֹʱ��
	 * @return
	 */
	public static double getWeeksTimeBetween(Date begin,Date end) {
		Long time = getMillionTimeBetween(begin, end);
		double time1 = time==null?0:time;
		return time1/WEEK_TIME;
	}
	/**
	 * ��ȡ��ʱ�������
	 * @param begin ��ʼʱ��
	 * @param end ��ֹʱ��
	 * @return
	 */
	public static int getMonthsTimeBetween(Date begin,Date end) {
		try {
			ParamCheckUtils.paramIsNull(begin, "ʱ��Ϊnull");
			ParamCheckUtils.paramIsNull(end, "ʱ��Ϊnull");
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
	 * ��ȡ��ʱ�������
	 * @param begin ��ʼʱ��
	 * @param end ��ֹʱ��
	 * @return
	 */
	public static int getYearsTimeBetween(Date begin,Date end) {
		try {
			ParamCheckUtils.paramIsNull(begin, "ʱ��Ϊnull");
			ParamCheckUtils.paramIsNull(end, "ʱ��Ϊnull");
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
	 * �������ʱ��֮ǰ��֮�������
	 * @param oldDate ԭʱ��
	 * @param time ����
	 * @param way ������λ����0����1��ʱ2����3����4����5����6���μ�������_WAY��β�ı�����
	 * @return
	 */
	public static Date getDateAfterTime(Date oldDate,int time,int way) {
		try {
			ParamCheckUtils.paramIsNull(oldDate, "ʱ��Ϊnull");
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
	 * ԭʱ�����ʱ��֮���ʱ��
	 * @param oldDate ԭʱ��
	 * @param time ����
	 * @param way ��λ���¡��꣩
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
	 * ������Ӧ�ĺ�����
	 * @param time ʱ����
	 * @param way ��λ(�롢�֡�ʱ���졢��)
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
