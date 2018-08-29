package base;


import java.util.Properties;

import org.junit.Test;

public class BaseTest {
	@Test
	public void  testAssert() {
		/*assert 1==1;
		System.out.println("a....");
		assert 1!=1;
		System.out.println("b....");*/
		
		int a = -1;
		assert 1==1:a=1;
		System.out.println("a...."+a);
		try {
			assert 1!=1:a=2;
			System.out.println("000");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("b...."+a);
		}
		System.out.println("111");
	}
	@Test
	public void test1() {
		Properties p = System.getProperties();
		System.out.println(p.getProperty("user.home"));
	}
}
