package string;

import java.util.StringTokenizer;

public class StringAbout {
	/**
	 * 
	 * 2018-05-12 12:36:50
	 * @param str 要解析的字符窜
	 * @param delim 分隔符
	 */
	public static void testStringToken(String str, String delim) {
		StringTokenizer st = new StringTokenizer(str, delim) ;
		while(st.hasMoreElements()) {
			System.out.println(st.nextElement());
		}
	}
}
