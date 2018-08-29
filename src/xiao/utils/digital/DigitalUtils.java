package xiao.utils.digital;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 数字处理工具类
 * @author Administrator
 *
 */
public class DigitalUtils {
	/**
	 * 允许相同
	 */
	public static final int SAME_YES = 0;
	
	/**
	 * 不允许相同
	 */
	public static final int SAME_NO = 1;
	private static BigDecimal bigDecimal ;
	/**
	 * 对数据进行格式化处理
	 * @param digital 待处理的数据
	 * @param n 保留小数后的位数
	 * @param roundWay 舍入方式(从BigDecimal获取)
	 * @return
	 */
	public static double formatPointByN(double digital,int n,int roundWay) {
		bigDecimal = new BigDecimal(digital);
		return bigDecimal.setScale(n,roundWay).doubleValue();
	}
	/**
	 * 产生随机数
	 * @param begin 开始
	 * @param end 截止
	 * @param n 大小
	 * @param way 产生方式（详见本类final修饰的属性,或0不去重，1去重）
	 * @return
	 */
	public static int[] getRandomInt(int begin ,int end ,int n, int way) {
		
		if(n<=0) {
			throw new IllegalArgumentException("参数n不能小于1");
		}
		
		if(way<0||way>1) {
			way = 0;
		}
		if(begin>end) {
			int temp = begin;
			begin = end;
			end = temp;
		}
		if(way==1 && n>end-begin) {
			throw new IllegalArgumentException("参数n不能大于end-begin的绝对值");
		}
		int []array = new int[n];
		Random rand = new Random();
		int count = 0;
		while(count<n) {
			int temp = rand.nextInt(end-begin+1)+begin;
			if(way==1) {
				if(checkIsSame(array,temp)) {
					continue;
				}else {
					array[count++] = temp;
				}
			}else {
				array[count++]=temp;
			}
		}
		return array;
	}
	/**
	 * 验证一个数是否存在与一个数组中
	 * @param array 数组
	 * @param temp 待验证的数
	 * @return
	 */
	private static boolean checkIsSame(int[] array, int temp) {
		for (int i = 0; i < array.length; i++) {
			if(temp==array[i]) {
				return true;
			}
		}
		return false;
	}
}
