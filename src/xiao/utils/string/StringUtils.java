package xiao.utils.string;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import xiao.utils.check.ParamCheckUtils;

/**
 * 
 * @author xjl
 * 2018-04-24 17:13:49
 * 
 * 对字符串处理的工具类
 */
public class StringUtils {
	/**
	 * 2018-04-24 17:18:06
	 * 
	 * 统计原字符串出现子字符串的次数
	 * @param string 原字符串
	 * @param str 子字符串
	 * @param way 统计方式（true：允许重复；false：不允许重复）
	 * （字符允许重复使用："babab",出现两次"bab"）
	 * @return 返回子字符串出现的次数
	 */
	public static int sunStringCount(String string,String str,boolean way) {
		checkParam(string,str);
		if(string.length()<str.length()) {
			return 0;
		}
		int n = 0;
		int index = string.indexOf(str);
		while(index>=0) {
			n++;
			if(way) {
				string = string.substring(index+1);
			}else {
				string = string.substring(index+str.length());
			}
			
			index = string.indexOf(str);
		}
		return n;
	}
	/**
	 * 划分单词(仅限英文单词)
	 * 2018-04-25 15:39:09
	 * @param string 原字符串
	 * @return 单词集合
	 */
	public static List<String> spiltworldInString(String string) {
		try {
			ParamCheckUtils.paramIsNull(string, "string为null");
			ParamCheckUtils.paramIsBlank(string, "string是空字符串");
		}catch(Exception e) {
			e.printStackTrace();
		}
		char []wordChar = string.toCharArray();
		List<String> words = new ArrayList<String>();
		String word = "";
		for (int i = 0; i < wordChar.length; i++) {
			
			if(word.length()>0) {//解决这种单词缩写 ：I'm 
				if(wordChar[i]=='\'') {
					word+=wordChar[i];
					continue;
				}
			}
			if(wordChar[i]>='A'&&wordChar[i]<='Z' || wordChar[i]>='a'&&wordChar[i]<='z' ) {
				word+=wordChar[i];
				if(i==wordChar.length-1) {
					words.add(word);
				}
				continue;
			}
			if(word.length()>0) {
				words.add(word);
				word = "";
			}
			
		}
		
		return words;
	}
	/**
	 * 统计指定单词出现的次数
	 * 2018-04-25 15:02:45
	 * @param string 原字符串
	 * @param word 指定单词
	 * @return
	 */
	public static int wordCountInString(String string,String word) {
		checkParam(string,word);
		if(!ParamCheckUtils.wordIsIllgal(word)) {
			return 0;
		}
		if(string.length()<word.length()) {
			return 0;
		}
		int n = 0;
		List<String> words = spiltworldInString(string);
		for (String str : words) {
			if(word.equals(str)) {
				n++;
			}
		}
		return n;
	}
	/**
	 * 统计指定文件出现单词的次数
	 * 2018-04-25 15:19:18
	 * @param file 指定文件
	 * @param word 单词
	 * @return 出现次数
	 */
	public static int wordCountInFile(File file,String word) {
		try {
			ParamCheckUtils.paramIsNull(word, "word为null");
			ParamCheckUtils.paramIsBlank(word, "word是空字符串");
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(!ParamCheckUtils.wordIsIllgal(word)) {
			return 0;
		}
		
		FileReader fr = null;
		BufferedReader br = null;
		int n = 0;
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String line ="";
			while((line=br.readLine())!=null){
				if(line.length()>0) {
					n+=wordCountInString(line, word);
				}
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
	/**
	 * 统计总的单词数
	 * 2018-04-25 15:02:45
	 * @param string 原字符串
	 * @return
	 */
	public static int wordsCountInString(String string) {
		try {
			ParamCheckUtils.paramIsNull(string, "原字符串为null");
			ParamCheckUtils.paramIsBlank(string, "原字符串为空字符串");
		} catch (Exception e) {
			e.printStackTrace();
		}
		int n = spiltworldInString(string).size();
		return n;
	}
	/**
	 * 统计指定文件单词总数
	 * 2018-04-25 15:19:18
	 * @param file 指定文件
	 * @return 单词总数
	 */
	public static int wordsCountInFile(File file) {
		FileReader fr = null;
		BufferedReader br = null;
		int n = 0;
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String line ="";
			while((line=br.readLine())!=null){
				if(line.length()>0) {
					n+=wordsCountInString(line);
				}
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
	/**
	 * 统计字符在指定文件出现的位置
	 * 2018-04-25 17:41:20
	 * @param file 指定文件
	 * @param str 指定字符串
	 * @return 位置
	 */
	public static TreeMap<Integer,ArrayList<Integer>> strPositionInFile(File file,String str){
		try {
			ParamCheckUtils.paramIsNull(str, "str为null");
			ParamCheckUtils.paramIsBlank(str, "str是空字符串");
		}catch(Exception e) {
			e.printStackTrace();
		}
		ArrayList<Integer> list = null;;
		TreeMap<Integer,ArrayList<Integer>> map = new TreeMap<Integer,ArrayList<Integer>>();
		FileReader fr = null;
		BufferedReader br = null;
		int row = 0;//行号
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String line ="";
			while((line=br.readLine())!=null){
				row++;
				if(line.length()>0) {
					list = strPositionInString(line, str);
					if(list!=null&&list.size()>0) {
						map.put(row, list);
					}
				}
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 指定子字符串在原字符串出现的位置
	 * 2018-04-25 17:47:56
	 * @param string 原字符串
	 * @param str 子字符串
	 * @return 位置
	 */
	public static ArrayList<Integer> strPositionInString(String string,String str){
		checkParam(string, str);
		if(string.length()<str.length()) {
			return null;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		int n = 0;
		while(true) {
			int index = string.indexOf(str);
			if(index<0) {
				break;
			}
			list.add(index+n);
			n+=(index+str.length());
			string=string.substring(index+str.length());
		}
		
		return list;
	}
	/**
	 * 验证参数
	 * 2018-04-25 14:01:50
	 * @param string
	 * @param str
	 */
	private static void checkParam(String string, String str) {
		try {
			ParamCheckUtils.paramIsNull(string, "原字符串为null");
			ParamCheckUtils.paramIsNull(str, "子字符串为null");
			ParamCheckUtils.paramIsBlank(string, "原字符串为空字符串");
			ParamCheckUtils.paramIsBlank(str, "子字符串为空字符串");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
