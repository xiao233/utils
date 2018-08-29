package scoket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import javax.swing.JTextArea;

public class BroadCast extends Thread{
     public int port=0;  //组播端口
     String Ip="";       //组播ip
     JTextArea recTextArea;
     String MyMessage="";
     String name="";    //昵称
     MulticastSocket multicastSocket=null;
    public BroadCast(int port,String Ip,String name,JTextArea recTextArea)//构造函数
    {
        this.port=port;
        this.Ip=Ip;
        this.name=name;
        this.recTextArea=recTextArea;
    }

    public void sendMessage(String message)//组播成员发送信息
    {
        try{
                String string=this.name;
                string+="："+message;
                message=string+"\n";

                byte[] buf=message.getBytes();
                DatagramPacket datagramPacket=new DatagramPacket(buf, buf.length);
                InetAddress address=InetAddress.getByName(Ip);
                datagramPacket.setAddress(address);
                datagramPacket.setPort(port);
                multicastSocket.send(datagramPacket);
            }
        catch(IOException e)
        {
            e.printStackTrace();
        }   
    }

    public void StopChat()
    { 
         sendMessage("组播用户下线"); //提示 组播用户下线
    }

    public void ClearScreen()
    {
        recTextArea.setText("");  //清空 “接收发信息”框
    }

    public void run() {//启动线程
            try{
                multicastSocket=new MulticastSocket(this.port);
                InetAddress address=InetAddress.getByName(Ip);
                multicastSocket.joinGroup(address);
                sendMessage("组播用户上线");   //提示 组播用户上线

                while(true){
                    byte[] buf=new byte[1024];
                    DatagramPacket datagramPacket=new DatagramPacket(buf, buf.length);
                    multicastSocket.receive(datagramPacket);

                    String string=new String(datagramPacket.getData(),0,datagramPacket.getLength());
                    this.recTextArea.append(string);        
                }
            }catch(IOException e)
            {
                e.printStackTrace();
            }
            multicastSocket.close();    
    }
}
