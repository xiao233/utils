package scoket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServicePragram extends Thread{
	private Socket socket;

	public ServicePragram(Socket socket) {
		super();
		this.socket = socket;
	}
	
	@Override
	public void run() {
		servicePragram();
	}

	private void servicePragram() {
		InputStream is= null;
		BufferedReader br = null;
		
		try {
			is = socket.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String content = "";
			while((content=br.readLine())!=null) {
				System.out.println(content);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
