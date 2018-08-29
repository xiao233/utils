package xiao.utils.digital;

import java.math.BigDecimal;
import java.util.Random;

/**
 * ���ִ�������
 * @author Administrator
 *
 */
public class DigitalUtils {
	/**
	 * ������ͬ
	 */
	public static final int SAME_YES = 0;
	
	/**
	 * ��������ͬ
	 */
	public static final int SAME_NO = 1;
	private static BigDecimal bigDecimal ;
	/**
	 * �����ݽ��и�ʽ������
	 * @param digital �����������
	 * @param n ����С�����λ��
	 * @param roundWay ���뷽ʽ(��BigDecimal��ȡ)
	 * @return
	 */
	public static double formatPointByN(double digital,int n,int roundWay) {
		bigDecimal = new BigDecimal(digital);
		return bigDecimal.setScale(n,roundWay).doubleValue();
	}
	/**
	 * ���������
	 * @param begin ��ʼ
	 * @param end ��ֹ
	 * @param n ��С
	 * @param way ������ʽ���������final���ε�����,��0��ȥ�أ�1ȥ�أ�
	 * @return
	 */
	public static int[] getRandomInt(int begin ,int end ,int n, int way) {
		
		if(n<=0) {
			throw new IllegalArgumentException("����n����С��1");
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
			throw new IllegalArgumentException("����n���ܴ���end-begin�ľ���ֵ");
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
	 * ��֤һ�����Ƿ������һ��������
	 * @param array ����
	 * @param temp ����֤����
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
