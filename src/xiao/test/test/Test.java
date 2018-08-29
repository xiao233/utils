package xiao.test.test;

import xiao.utils.digital.DigitalUtils;
import xiao.utils.sort.SortUtils;

public class Test {

	public static void main(String[] args) {
		try {
			int []array = DigitalUtils.getRandomInt(10, 50, 20, DigitalUtils.SAME_YES);
			show(array);
			
			int []array3 = SortUtils.insertSort(array, false);
			show(array3);
			int []array4 = SortUtils.quickSort(array);
			show(array4);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private static void show(int[] randomInt) {
		for (int i = 0; i < randomInt.length;) {
			System.out.print(randomInt[i]+" ");
			i++;
			if(i%10==0) {
				System.out.println();
			}
		}
		System.out.println("\n====================================");
	}

}
