package xiao.utils.check;
/**
 * ������֤����
 * @author Administrator
 *
 */
public class ParamCheckUtils {
	/**
	 * ��֤�����Ƿ�Ϊnull
	 * @param obj ����֤����
	 * @param msg ��ʾ��Ϣ
	 */
	public static void paramIsNull(Object obj,String msg) {
		if(obj==null) {
			throw new IllegalArgumentException(msg);
		}
	}
	/**
	 * ��֤�����Ƿ�Ϊ���ַ���
	 * @param obj ����֤����
	 * @param msg ��ʾ��Ϣ
	 */
	public static void paramIsBlank(String obj,String msg) {
		if(obj.length()==0) {
			throw new IllegalArgumentException(msg);
		}
	}
	/**
	 * ��֤���ʸ�ʽ�ĺϷ���
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
	 * ��֤�ַ����Ƿ�ƥ���ض���������ʽ
	 * @param param �ַ���
	 * @param regular ������ʽ
	 * @return
	 */
	public static boolean paramMatchRegular(String param,String regular) {
		if(param == null) {
			throw new IllegalArgumentException("����paramΪnullֵ");
		}
		if(regular ==null) {
			throw new IllegalArgumentException("����regularΪnullֵ");
		}
		return param.matches(regular);
	}
}
