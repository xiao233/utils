package scoket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import javax.swing.JTextArea;

public class BroadCast extends Thread{
     public int port=0;  //�鲥�˿�
     String Ip="";       //�鲥ip
     JTextArea recTextArea;
     String MyMessage="";
     String name="";    //�ǳ�
     MulticastSocket multicastSocket=null;
    public BroadCast(int port,String Ip,String name,JTextArea recTextArea)//���캯��
    {
        this.port=port;
        this.Ip=Ip;
        this.name=name;
        this.recTextArea=recTextArea;
    }

    public void sendMessage(String message)//�鲥��Ա������Ϣ
    {
        try{
                String string=this.name;
                string+="��"+message;
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
         sendMessage("�鲥�û�����"); //��ʾ �鲥�û�����
    }

    public void ClearScreen()
    {
        recTextArea.setText("");  //��� �����շ���Ϣ����
    }

    public void run() {//�����߳�
            try{
                multicastSocket=new MulticastSocket(this.port);
                InetAddress address=InetAddress.getByName(Ip);
                multicastSocket.joinGroup(address);
                sendMessage("�鲥�û�����");   //��ʾ �鲥�û�����

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
