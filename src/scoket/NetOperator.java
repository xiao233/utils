package scoket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
/**
 * 有关网络的一些方法
 * @author xjl
 * 2018-05-04 14:25:46
 */
public class NetOperator {
	BufferedWriter bw = null;
	Set<String> set = null;
	@Before
	public void init() {
		String fileName = "C:\\Users\\Administrator\\Desktop\\a.html";
		try {
			bw = new BufferedWriter(new FileWriter(new File(fileName)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		set = new HashSet<>();
	}
	@Test
	public void testNet() {
		InetAddress address = null;
		String host = "www.baidu.com";
		try {
			address = InetAddress.getByName(host);//通过域名获取InetAddress对象
			System.out.println(address.getHostAddress());
			
			byte []ip = {127,0,0,1};//IPv4 4个字节，IPv6 16个字节
			address = InetAddress.getByAddress(ip);//通过ip地址获取InetAddress对象
			System.out.println(address.getHostName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void checkPort() {
		// 查看端口是否被占用
		String host = "localhost";
		
		/**
		 * 此类实现客户端套接字（也可以就叫“套接字”）。套接字是两台机器间通信的端点。 
		 */
		Socket scoket = null;

		for (int i = 0; i < 1024; i++) {
			try {
				System.out.print("查看端口： " + i);
				scoket = new Socket(host, i);//创建一个流套接字并将其连接到指定主机上的指定端口号。
				System.out.println(",已经被占用");
				
			} catch (UnknownHostException e) {//如果无法确定主机的 IP 地址。 
				System.out.println("找不到主机");
				break;
			} catch (IOException e) {// - 如果创建套接字时发生 I/O 错误，如端口被占用
				System.out.println("，未被占用");
			}
		}
	}
	
	@Test
	public void caulSize() {
		//查看远程文件的大小
		
		
		String path = "http://www.runoob.com/wp-content/themes/runoob/assets/img/newlogo.png";
		/**
		 * 类 URL 代表一个统一资源定位符，它是指向互联网“资源”的指针。
		 * 资源可以是简单的文件或目录，也可以是对更为复杂的对象的引用，例如对数据库或搜索引擎的查询
		 */
		URL url = null;
		
		
		try {
			url = new URL(path);//根据 String 表示形式创建 URL 对象。
			
		} catch (MalformedURLException e) {//- 如果字符串指定未知协议。
			e.printStackTrace();
		} 
		
		/**
		 * 抽象类 URLConnection 是所有类的超类，
		 * 它代表应用程序和 URL 之间的通信链接。
		 * 此类的实例可用于读取和写入此 URL 引用的资源。
		 */
		URLConnection conn = null;
		long size = 0L;
		try {
			conn = url.openConnection();// 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
			size = conn.getContentLength();//返回 content-length 头字段的值。
			System.out.println(size);
			conn.getInputStream().close();//关闭连接
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void catchNet() {
		//抓取网页
		
		String host = "http://www.runoob.com/java/java-networking.html";
		String fileName = "C:\\Users\\Administrator\\Desktop\\data.html";
		URL url = null;
		BufferedReader br = null; 
		BufferedWriter bw = null;
		try {
			url = new URL(host);
			
			br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
			
			bw = new BufferedWriter(new FileWriter(new File(fileName)));
			
			String line = null;
			while((line = br.readLine())!=null){
				bw.write(line);
				bw.newLine();
				bw.flush();
			}
			
			br.close();
			bw.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void catchNet1() {
		String host = "http://www.runoob.com/java/java-networking.html";
		
		catchNet(host);
	}
	public void catchNet(String host) {
		URL url = null;
		BufferedReader br = null; 
	
		try {
			url = new URL(host);
			
			br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
			
			
			String line = null;
			String title="";
			String root = "//www.runoob.com";
			while((line = br.readLine())!=null){
				if(line.indexOf("<a href=")>=0) {
					line = line.substring(line.indexOf("<a"), line.indexOf("</a>")+4);
					line = line.substring(line.indexOf("<a href=\"")+9,line.length()-1);
					title=line.substring(line.indexOf(">")+1,line.indexOf("<"));
					line = line.substring(0,line.indexOf("\""));
					System.out.println(title+"....."+line);
					if(line.indexOf(root)<0) {
						line="http:"+root+line;
					}else {
						if(line.indexOf("http")<0) {
							line="http:"+line;
						}
					}
					
					if(!set.contains(line)) {
						
						bw.write(title+"="+line);
						bw.newLine();
						bw.write("<br/>");
						bw.newLine();
						bw.flush();
						set.add(line);
						catchNet(line);
					}
				}
			}
			
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
