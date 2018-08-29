package scoket;

import java.net.Socket;
import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class ClientPragram extends Thread{
	private Socket socket;
	private String name;
	
	public  ClientPragram(Socket socket,String name) {
		this.socket=socket;
		this.name=name;
	}
	
	@Override
	public void run() {
		clientPragram();
	}

	private void clientPragram() {
		OutputStream os = null;
		BufferedWriter bw = null;
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		System.out.print(name+": ");
		String content = scan.nextLine();
		try {
			os = socket.getOutputStream();
			bw = new BufferedWriter(new OutputStreamWriter(os));
			bw.write(content);
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
