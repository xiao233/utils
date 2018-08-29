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
 * ���ַ�������Ĺ�����
 */
public class StringUtils {
	/**
	 * 2018-04-24 17:18:06
	 * 
	 * ͳ��ԭ�ַ����������ַ����Ĵ���
	 * @param string ԭ�ַ���
	 * @param str ���ַ���
	 * @param way ͳ�Ʒ�ʽ��true�������ظ���false���������ظ���
	 * ���ַ������ظ�ʹ�ã�"babab",��������"bab"��
	 * @return �������ַ������ֵĴ���
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
	 * ���ֵ���(����Ӣ�ĵ���)
	 * 2018-04-25 15:39:09
	 * @param string ԭ�ַ���
	 * @return ���ʼ���
	 */
	public static List<String> spiltworldInString(String string) {
		try {
			ParamCheckUtils.paramIsNull(string, "stringΪnull");
			ParamCheckUtils.paramIsBlank(string, "string�ǿ��ַ���");
		}catch(Exception e) {
			e.printStackTrace();
		}
		char []wordChar = string.toCharArray();
		List<String> words = new ArrayList<String>();
		String word = "";
		for (int i = 0; i < wordChar.length; i++) {
			
			if(word.length()>0) {//������ֵ�����д ��I'm 
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
	 * ͳ��ָ�����ʳ��ֵĴ���
	 * 2018-04-25 15:02:45
	 * @param string ԭ�ַ���
	 * @param word ָ������
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
	 * ͳ��ָ���ļ����ֵ��ʵĴ���
	 * 2018-04-25 15:19:18
	 * @param file ָ���ļ�
	 * @param word ����
	 * @return ���ִ���
	 */
	public static int wordCountInFile(File file,String word) {
		try {
			ParamCheckUtils.paramIsNull(word, "wordΪnull");
			ParamCheckUtils.paramIsBlank(word, "word�ǿ��ַ���");
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
	 * ͳ���ܵĵ�����
	 * 2018-04-25 15:02:45
	 * @param string ԭ�ַ���
	 * @return
	 */
	public static int wordsCountInString(String string) {
		try {
			ParamCheckUtils.paramIsNull(string, "ԭ�ַ���Ϊnull");
			ParamCheckUtils.paramIsBlank(string, "ԭ�ַ���Ϊ���ַ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		int n = spiltworldInString(string).size();
		return n;
	}
	/**
	 * ͳ��ָ���ļ���������
	 * 2018-04-25 15:19:18
	 * @param file ָ���ļ�
	 * @return ��������
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
	 * ͳ���ַ���ָ���ļ����ֵ�λ��
	 * 2018-04-25 17:41:20
	 * @param file ָ���ļ�
	 * @param str ָ���ַ���
	 * @return λ��
	 */
	public static TreeMap<Integer,ArrayList<Integer>> strPositionInFile(File file,String str){
		try {
			ParamCheckUtils.paramIsNull(str, "strΪnull");
			ParamCheckUtils.paramIsBlank(str, "str�ǿ��ַ���");
		}catch(Exception e) {
			e.printStackTrace();
		}
		ArrayList<Integer> list = null;;
		TreeMap<Integer,ArrayList<Integer>> map = new TreeMap<Integer,ArrayList<Integer>>();
		FileReader fr = null;
		BufferedReader br = null;
		int row = 0;//�к�
		
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
	 * ָ�����ַ�����ԭ�ַ������ֵ�λ��
	 * 2018-04-25 17:47:56
	 * @param string ԭ�ַ���
	 * @param str ���ַ���
	 * @return λ��
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
	 * ��֤����
	 * 2018-04-25 14:01:50
	 * @param string
	 * @param str
	 */
	private static void checkParam(String string, String str) {
		try {
			ParamCheckUtils.paramIsNull(string, "ԭ�ַ���Ϊnull");
			ParamCheckUtils.paramIsNull(str, "���ַ���Ϊnull");
			ParamCheckUtils.paramIsBlank(string, "ԭ�ַ���Ϊ���ַ���");
			ParamCheckUtils.paramIsBlank(str, "���ַ���Ϊ���ַ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
