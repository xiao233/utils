package xiao.utils.sort;

import java.util.Arrays;

import xiao.utils.check.ParamCheckUtils;

/**
 * 数的排序
 * @author Administrator
 *
 */
public class SortUtils {
	/**
	 * 选择排序
	 * @param oldArray 原数组
	 * @param way 排序方式（true升序，false降序）
	 * @return
	 */
	public static int[] selectSort(int []oldArray,boolean way) {
		try {
			ParamCheckUtils.paramIsNull(oldArray, "oldArray为null");
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			return null;
		}
		int []newArray = Arrays.copyOf(oldArray, oldArray.length);
		for (int i = 0; i < newArray.length-1; i++) {
			int key = i;
			if(way) {
				for (int j = i+1; j < newArray.length; j++) {
					if(newArray[key]>newArray[j]) {
						key = j;
					}
				}
			}else {
				for (int j = i+1; j < newArray.length; j++) {
					if(newArray[key]<newArray[j]) {
						key = j;
					}
				}
			}
			if(key!= i) {
				int temp = newArray[i];
				newArray[i] = newArray[key];
				newArray[key] = temp;
			}
		}
		return newArray;
	}
	/**
	 * 冒泡排序
	 * @param oldArray 原数组
	 * @param way 排序方式（true升序，false降序）
	 * @return
	 */
	public static int[] bubbleSort(int []oldArray,boolean way) {
		try {
			ParamCheckUtils.paramIsNull(oldArray, "oldArray为null");
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			return null;
		}
		int []newArray = Arrays.copyOf(oldArray, oldArray.length);
		if(way) {
			for (int i = 0; i < newArray.length-1; i++) {
				for (int j = 0; j < newArray.length-i-1; j++) {
					if(newArray[j]>newArray[j+1]) {
						int temp = newArray[j];
						newArray[j] = newArray[j+1];
						newArray[j+1] = temp;
					}
				}
			}
		}else {
			for (int i = 0; i < newArray.length-1; i++) {
				for (int j = 0; j < newArray.length-i-1; j++) {
					if(newArray[j]<newArray[j+1]) {
						int temp = newArray[j];
						newArray[j] = newArray[j+1];
						newArray[j+1] = temp;
					}
				}
			}
		}
		
		return newArray;
	}
	/**
	 * 插入排序
	 * @param oldArray 原数组
	 * @param way 排序方式（true升序，false降序）
	 * @return
	 */
	public static int[] insertSort(int []oldArray,boolean way) {
		try {
			ParamCheckUtils.paramIsNull(oldArray, "oldArray为null");
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			return null;
		}
		int []newArray = Arrays.copyOf(oldArray, oldArray.length);
		if(way) {
			for (int i = 1; i < newArray.length; i++) {
				int temp = newArray[i];
				int j = i-1;
				//找到元素插入的位置
				for (; j >= 0; j--) {
					if(temp<newArray[j]) {
						newArray[j+1]=newArray[j];
					}else {
						break;
					}
				}
				
			newArray[j+1]=temp;
			}
		}else {
			for (int i = 1; i < newArray.length; i++) {
				int temp = newArray[i];
				int j = i-1;
				//找到元素插入的位置
				for (; j >= 0; j--) {
					if(temp>newArray[j]) {
						newArray[j+1]=newArray[j];
					}else {
						break;
					}
				}
				
			newArray[j+1]=temp;
			}
		}
		
		
		return newArray;
	}
	/**
	 * 基数排序
	 * @param oldArray 原数组
	 * @param way 排序方式（true升序，false降序）
	 * @return
	 */
	public static int[] cardinalSort(int []oldArray,boolean way) {
		try {
			ParamCheckUtils.paramIsNull(oldArray, "oldArray为null");
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			return null;
		}
		int []newArray = Arrays.copyOf(oldArray, oldArray.length);
		/**
		 * 
		 * 
		 * will do
		 * 
		 * 
		 */
		
		return newArray;
	}
	/**
	 * 快速排序
	 * @param oldArray 原数组
	 * @return
	 */
	public static int[] quickSort(int []oldArray) {
		try {
			ParamCheckUtils.paramIsNull(oldArray, "oldArray为null");
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			return null;
		}
		int []newArray = Arrays.copyOf(oldArray, oldArray.length);
		quickSortMethod(newArray,0,newArray.length-1);
		
		return newArray;
	}
	private static void quickSortMethod(int[] newArray, int left, int right) {
		
		if(left<right) {
			int i = findIndex(newArray,left,right);
			quickSortMethod(newArray,left,i-1);
			quickSortMethod(newArray,i+1,right);
		}
	}
	private static int findIndex(int[] newArray, int left, int right) {
		int base = newArray[left];
		while(left<right) {
			while(left<right && base<=newArray[right])
				right--;
			newArray[left] = newArray[right];
			while(left<right && base>=newArray[left])
				left++;
			newArray[right] = newArray[left];
		}
		newArray[left] = base;
		return left;
	}
	/**
	 * 删除重复元素，数据在新数组中的顺序和原数组保持一致
	 * @param oldArray 原数组
	 * @return
	 */
	public static int[] canelSameData(int []oldArray){
		try {
			ParamCheckUtils.paramIsNull(oldArray, "oldArray为null");
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			return null;
		}
		int []newArray = new int[oldArray.length];
		newArray[0]=oldArray[0];
		int count = 1;
		for (int i = 1; i < oldArray.length; i++) {
			int j = 0;
			for (; j < count; j++) {
				if(oldArray[i]==newArray[j]) {
					break;
				}
			}
			if(j==count) {
				newArray[count]=oldArray[i];
				count++;
			}
		}
		return Arrays.copyOf(newArray, count);
	}

}
