package xiao.test.test;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import email.SendEmailUtils;
import file.FolderOperate;
import table.TableOperate;
import xiao.test.entrties.Student;
import xiao.test.entrties.Teacher;
import xiao.test.entrties.Worker;
import xiao.utils.check.ParamCheckUtils;
import xiao.utils.digital.DigitalUtils;
import xiao.utils.object.ObjectUtils;
import xiao.utils.sort.SortUtils;
import xiao.utils.string.StringUtils;
import xiao.utils.time.TimeUtils;

public class TestCopy {
	@Before
	public void init() {
	}
	@Test
	public void testCopyObject() {
		Teacher src = new Teacher();
		src.setAge(18);
		src.setName("张虎");
		src.setNumber("123");
		Student dis = new Student();
		Worker dis1 = new Worker();
		ObjectUtils.objectPropertiesCopy(src, dis);
		
		ObjectUtils.objectPropertiesCopy(src, dis1);
		System.out.println(dis1);
		//ObjectCopyUtils.objectInit(dis1, "哈哈哈", 0, '男');
		//ObjectCopyUtils.objectInitString(dis1, "那你呢");
		ObjectUtils.objectInitCharacter(dis1, 'h');
		System.out.println(dis1);
	}
	
	@Test
	public void testTime() {
		String endTime = TimeUtils.getTimeByFormat(new Date(),TimeUtils.FORMAT_DATE_TIME24);
		System.out.println(endTime);
		Date begin = TimeUtils.changeToDate("2016-02-17 09:53:44",TimeUtils.FORMAT_DATE_TIME24);
		Date end = TimeUtils.changeToDate(endTime, TimeUtils.FORMAT_DATE_TIME24);
		Long time = TimeUtils.getMillionTimeBetween(begin, end);
		System.out.println(TimeUtils.getSecondsTimeBetween(begin, end));
		System.out.println(TimeUtils.getMinutesTimeBetween(begin, end));
		System.out.println(DigitalUtils.formatPointByN(TimeUtils.getHoursTimeBetween(begin, end), 2, BigDecimal.ROUND_HALF_UP));
		System.out.println(TimeUtils.getDaysTimeBetween(begin, end));
		System.out.println(TimeUtils.getWeeksTimeBetween(begin, end));
		
		System.out.println(TimeUtils.getMonthsTimeBetween(begin, end));
		System.out.println(TimeUtils.getYearsTimeBetween(begin, end));
		
		Date date = new Date();
		System.out.println("-----------"+date);
		System.out.println(TimeUtils.getDateAfterTime(null, -6, 3));
	}
	
	@Test
	public void testDigital() {
		System.out.println(DigitalUtils.formatPointByN(3.1415926, 3, BigDecimal.ROUND_HALF_UP));
		try {
			int []array = DigitalUtils.getRandomInt(10, 50, 20, DigitalUtils.SAME_YES);
			show(array);
			/*int []array0 = SortUtils.canelSameData(array);
			show(array0);
			int []array1 = SortUtils.selectSort(array0, false);
			show(array0);
			show(array1);
			int []array2 = SortUtils.bubbleSort(array, true);
			show(array2);*/
			int []array3 = SortUtils.insertSort(array, false);
			show(array3);
			int []array4 = SortUtils.quickSort(array);
			show(array4);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void testString() {
		/*int count = StringUtils.sunStringCount("abababbc", "aba",false);
		System.out.println(count);
		count = StringUtils.wordCountInString("  I'm .today is     sunshine, it is good day", "g");
		System.out.println(count);*/
		String string = "  	I'm .today \"is\"     sunshine, it is  good day"
				+ "";
		List<String> words = StringUtils.spiltworldInString(string);
		show(words);
		int n = StringUtils.wordsCountInString(string);
		System.out.println(n);
		n = StringUtils.wordCountInString(string, "I'm");
		System.out.println(n);
		boolean flag = ParamCheckUtils.wordIsIllgal("I'm");
		System.out.println(flag);
		
		n = StringUtils.wordCountInFile(new File("E:\\Eclipse\\xiao.utils\\src\\xiao\\test\\entrties\\Student.java"), "String");
		System.out.println(n);
		
		n = StringUtils.wordsCountInFile(new File("E:\\Eclipse\\xiao.utils\\src\\xiao\\test\\entrties\\Student.java"));
		System.out.println(n);
		
		List<Integer> position = StringUtils.strPositionInString(string, "day");
		System.out.println("size: "+position.size());
		show(position);
		
		TreeMap<Integer,ArrayList<Integer>> map = StringUtils.strPositionInFile(new File("E:\\Eclipse\\xiao.utils\\src\\xiao\\test\\entrties\\Student.java"), "String");
		show(map);
	}
	private void show(Map<Integer, ArrayList<Integer>> map) {
		Set<Entry<Integer,ArrayList<Integer>>> set = map.entrySet();
		for (Entry<Integer, ArrayList<Integer>> entry : set) {
			System.out.print(entry.getKey()+":  ");
			for (Integer colum : entry.getValue()) {
				System.out.print(colum+"  ");
			}
			System.out.println();
		}
		
	}
	private void show(int[] randomInt) {
		for (int i = 0; i < randomInt.length;) {
			System.out.print(randomInt[i]+" ");
			i++;
			if(i%10==0) {
				System.out.println();
			}
		}
		System.out.println("\n====================================");
	}
	private  <T> void show(List<T> randomInt) {
		for (T t : randomInt) {
			System.out.println(t);
		}
		System.out.println("\n====================================");
	}
	@Test
	public void testFolder() {
		File src = new File("E:\\Eclipse\\springIO\\src");
		File dis = new File("C:\\Users\\Administrator\\Desktop\\test");
		FolderOperate.copyFolder(src, dis);
		
		FolderOperate.showFileName(src,"");
		
		FolderOperate.findFile(src, "Impl");
		
	}
	@Test
	public void testTable() {
		//TableOperate.writeToTable(new File("C:\\Users\\Administrator\\Desktop\\mm.xlsx"));
		TableOperate.readerFromTable(new File("C:\\Users\\Administrator\\Desktop\\mm.xlsx"));
	}
	@Test
	public void testMail() {
		// 收件人电子邮箱
	      String to = "1462966599@qq.com";
	 
	      // 发件人电子邮箱
	      String from = "1336083256@qq.com";
	 
	      //授权密码
	      String pass = "edkmaocewbxqjbji";
	      // 指定发送邮件的主机为 smtp.qq.com
	      String host = "smtp.qq.com";  //QQ 邮件服务器
	      
	      //指定端口号
	      int port = 587; 
	      
	      String title ="api文档";
	      
	      String content = "各种api文档，注意查收";
	      
	      String files[] = {
	    		  "C:\\Users\\Administrator\\Desktop\\test\\mysql.properties",
	    		  "C:\\Users\\Administrator\\Desktop\\test\\spring.cfg.xml",
	    		   "C:\\Users\\Administrator\\Desktop\\mm.xlsx"};
	      
	      
	      SendEmailUtils.sendEmail(from, to, pass, host, port, title, content, files);
	}
	@Test
	public void testService() {
		
	}
	@Test
	public void testClient() {
		
	}
	@After
	public void destory() {
		
	}
}
