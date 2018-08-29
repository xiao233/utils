package thread;
/**
 * 多线程继承Thread类
 * @author xjl
 * 2018-05-03 10:41:33
 */
public class MyThread extends Thread{
	private String threadName;
	private static int n = 0;
	public MyThread() {
		
	}
	
	public MyThread(String threadName) {
		this.threadName = threadName;
	}
	@Override
	public void run() {
		while(true) {
			n++;
			System.out.println(this.getName()+": "+threadName+"...正在运行...."+n);
		}
	}
}
