package thread;
import org.junit.Test;
public class TestThread {
	@Test
	public void test() {
		Thread t1 = new MyThread("������");
		t1.start();
		Thread t2 = new MyThread("�������");
		t2.start();
	}
}
