package email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import xiao.utils.time.TimeUtils;
 
public class SendEmail
{
	 public static void main(String [] args) {
		 //��ʼʱ��
		 Long beginTime = 1525247638595L;
		 
		 //���ʱ��
		 Long addTime = 30*1000L;
		 
		 //��ֹʱ��
		 Long endTime = beginTime + addTime*10;
		 
		 //��ǰʱ��
		 Long currTime = null;
		 int i = 0;
		 while(true) {
			 currTime = System.currentTimeMillis();
			 if((currTime-beginTime)%addTime==0) {
				 String string = TimeUtils.getTimeByFormat(new Date(currTime), TimeUtils.FORMAT_DATE_TIME24);
				 sendEmail(string);
				 i++;
				 System.out.println(string +"����"+i+"���ʼ�");
			 }
			 if(currTime==endTime) {
				 break;
			 }
		 }
	   }
	 public static void sendEmail(String head) {

	      // �ռ��˵�������
	      String to = "1462966599@qq.com";
	 
	      // �����˵�������
	      String from = "1336083256@qq.com";
	 
	      // ָ�������ʼ�������Ϊ smtp.qq.com
	      String host = "smtp.qq.com";  //QQ �ʼ�������
	 
	      // ��ȡϵͳ����
	      Properties properties = System.getProperties();
	 
	      // �����ʼ�������
	      properties.setProperty("mail.smtp.host", host);
	      //��������˿�
	      properties.put("mail.smtp.port", 587);
	      //�û�������֤
	      properties.put("mail.smtp.auth", "true");
	      // ��ȡĬ��session����
	      Session session = Session.getDefaultInstance(properties,new Authenticator(){
	        public PasswordAuthentication getPasswordAuthentication()
	        {
	         return new PasswordAuthentication(from, "edkmaocewbxqjbji"); //�������ʼ��û���������
	        }
	       });
	 
	      try{
	         // ����Ĭ�ϵ� MimeMessage ����
	         MimeMessage message = new MimeMessage(session);
	 
	         // Set From: ͷ��ͷ�ֶ�
	         message.setFrom(new InternetAddress(from));
	 
	         // Set To: ͷ��ͷ�ֶ�
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));
	 
	         // Set Subject: ͷ��ͷ�ֶ�
	         message.setSubject(head);
	 
	         // ������Ϣ��
	         message.setText("This is actual message");
	 
	         // ������Ϣ
	         Transport.send(message);
	         System.out.println("Sent message successfully....from runoob.com");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	 }
}
