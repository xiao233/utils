package xiao.utils.check;
/**
 * 用于验证参数
 * @author Administrator
 *
 */
public class ParamCheckUtils {
	/**
	 * 验证参数是否为null
	 * @param obj 待验证参数
	 * @param msg 提示信息
	 */
	public static void paramIsNull(Object obj,String msg) {
		if(obj==null) {
			throw new IllegalArgumentException(msg);
		}
	}
	/**
	 * 验证参数是否为空字符串
	 * @param obj 待验证参数
	 * @param msg 提示信息
	 */
	public static void paramIsBlank(String obj,String msg) {
		if(obj.length()==0) {
			throw new IllegalArgumentException(msg);
		}
	}
	/**
	 * 验证单词格式的合法性
	 * 2018-04-25 16:38:49
	 * @param word
	 * @return
	 */
	public static boolean wordIsIllgal(String word) {
		if(word.indexOf('\'')==0||word.indexOf('\'')==word.length()-1) {
			return false;
		}
		if(word.indexOf('\'')>0) {
			String temp = word.substring(word.indexOf('\'')+1);
			if(temp.indexOf('\'')>=0) {
				return false;
			}
		}
		
		char []words = word.toCharArray();
		for (int i = 0; i < words.length; i++) {
			if(!(words[i]>='A'&&words[i]<='Z' || words[i]>='a'&&words[i]<='z' || words[i]=='\'')) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 验证字符串是否匹配特定的正则表达式
	 * @param param 字符串
	 * @param regular 正则表达式
	 * @return
	 */
	public static boolean paramMatchRegular(String param,String regular) {
		if(param == null) {
			throw new IllegalArgumentException("参数param为null值");
		}
		if(regular ==null) {
			throw new IllegalArgumentException("参数regular为null值");
		}
		return param.matches(regular);
	}
}
