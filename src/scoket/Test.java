package scoket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {
	@org.junit.Test
	public void client() {
		try {
			Socket socket = new Socket("localhost",8888);
			System.out.println("socket: "+socket);
			System.out.println(socket.getLocalAddress()+" : "+socket.getRemoteSocketAddress());
			ClientPragram cp = new ClientPragram(socket, "С��");
			cp.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@org.junit.Test
	public void service() {
		ServerSocket ssocket = null;
		try {
			ssocket = new ServerSocket(8888);
			
			System.out.println( "����������.....");
			while(true) {
				Socket socket = ssocket.accept();
				ServicePragram sp = new ServicePragram(socket);
				sp.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
