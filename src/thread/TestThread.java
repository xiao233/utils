package thread;
import org.junit.Test;
public class TestThread {
	@Test
	public void test() {
		Thread t1 = new MyThread("¹þ¹þ¹þ");
		t1.start();
		Thread t2 = new MyThread("·ç»ú·¿°É");
		t2.start();
	}
}
