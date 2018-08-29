package scoket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BroadCastTest{
    private JTextField portTxtFiled=new JTextField();
    private JTextField CastIPTxtFiled=new JTextField();
    private JTextField nameField=new JTextField();
    private JButton startChatBtn=new JButton("开始聊天");
    private JButton stopChatBtn=new JButton("断开");
    private JTextArea receiveMesArea=new JTextArea(); 

    JScrollPane jScrollPane=new JScrollPane(receiveMesArea);
    private JTextArea sendMesArea=new JTextArea(); 
    private JButton clearScrBtn=new JButton("清屏");
    private JButton sendBtn=new JButton("发送");
    private JButton quitBtn=new JButton("退出");

    private BroadCast broadCast=null;

    @SuppressWarnings("deprecation")
    public void InitFrame()
    {
        JFrame mainFrame=new JFrame();

        mainFrame.setLayout(null);
        mainFrame.setSize(600, 600);
        mainFrame.setLayout(null);

        JLabel JLabel32= new JLabel("组播昵称");
        JLabel32.setBounds(20, 20, 60, 20);
        mainFrame.add(JLabel32);
        nameField.setBounds(80, 20, 100, 20);
        mainFrame.add(nameField);

        JLabel JLabel2= new JLabel("端口号");
        JLabel2.setBounds(320, 20, 40, 20);
        mainFrame.add(JLabel2);
        portTxtFiled.setBounds(360, 20, 100, 20);
        mainFrame.add(portTxtFiled); 

        JLabel JLabel3= new JLabel("组播地址");
        JLabel3.setBounds(20, 80, 60, 20);
        mainFrame.add(JLabel3);
        CastIPTxtFiled.setBounds(80, 80, 100, 20);
        mainFrame.add(CastIPTxtFiled);    

        startChatBtn.setBounds(220, 80, 100, 20);
        stopChatBtn.setBounds(360, 80, 100, 20);
        mainFrame.add(startChatBtn);
        mainFrame.add(stopChatBtn);

        JLabel JLabel4= new JLabel("接收发消息");
        JLabel4.setBounds(20, 140, 100, 20);
        jScrollPane.setBounds(20,160,460,130);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainFrame.add(jScrollPane);    
        mainFrame.add(JLabel4);

        JLabel JLabel5= new JLabel("发送消息");
        JLabel5.setBounds(20, 300, 100, 20);
        sendMesArea.setBounds(20, 320, 460, 100);
        mainFrame.add(sendMesArea);
        mainFrame.add(JLabel5);

        clearScrBtn.setBounds(230, 460, 100, 20);//清屏
        mainFrame.add(clearScrBtn);
        sendBtn.setBounds(380, 460, 100, 20); //发送
        mainFrame.add(sendBtn);
        quitBtn.setBounds(80, 460, 100, 20);  //退出
        mainFrame.add(quitBtn);
        mainFrame.show();
        startChatBtn.setEnabled(true);
        stopChatBtn.setEnabled(false);
        sendBtn.setEnabled(false);

        startChatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            
                StartChat();
            }
            });
        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendMessage();
            }
            });
        clearScrBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClearScreen();
            }
            });
        stopChatBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startChatBtn.setEnabled(true);
                stopChatBtn.setEnabled(false);
                sendBtn.setEnabled(false);
                StopChat();
            }
            });
        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StopChat();
                System.exit(0);
            }
            });
    }

    public static void main(String[] args)
    {
        BroadCastTest client=new BroadCastTest();
        client.InitFrame();
    }

    public void StartChat()
    {
        String port= portTxtFiled.getText();
        String castIp=portTxtFiled.getText();
        if(!castIp.equals("") && !port.equals(""))
        {
            startChatBtn.setEnabled(false);
            stopChatBtn.setEnabled(true);
            sendBtn.setEnabled(true);
            broadCast=new BroadCast(Integer.parseInt(portTxtFiled.getText()),CastIPTxtFiled.getText(),nameField.getText(), receiveMesArea);
            broadCast.start();
        }       
    }

    public void SendMessage()
    {
        broadCast.sendMessage(sendMesArea.getText());
    }

    public void ClearScreen()
    {
        broadCast.ClearScreen();
    }

    @SuppressWarnings("deprecation")
    public void StopChat()
    {
        broadCast.StopChat();
        broadCast.stop();
    }
}