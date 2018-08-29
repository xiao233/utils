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
		 //开始时间
		 Long beginTime = 1525247638595L;
		 
		 //间隔时间
		 Long addTime = 30*1000L;
		 
		 //终止时间
		 Long endTime = beginTime + addTime*10;
		 
		 //当前时间
		 Long currTime = null;
		 int i = 0;
		 while(true) {
			 currTime = System.currentTimeMillis();
			 if((currTime-beginTime)%addTime==0) {
				 String string = TimeUtils.getTimeByFormat(new Date(currTime), TimeUtils.FORMAT_DATE_TIME24);
				 sendEmail(string);
				 i++;
				 System.out.println(string +"发送"+i+"封邮件");
			 }
			 if(currTime==endTime) {
				 break;
			 }
		 }
	   }
	 public static void sendEmail(String head) {

	      // 收件人电子邮箱
	      String to = "1462966599@qq.com";
	 
	      // 发件人电子邮箱
	      String from = "1336083256@qq.com";
	 
	      // 指定发送邮件的主机为 smtp.qq.com
	      String host = "smtp.qq.com";  //QQ 邮件服务器
	 
	      // 获取系统属性
	      Properties properties = System.getProperties();
	 
	      // 设置邮件服务器
	      properties.setProperty("mail.smtp.host", host);
	      //设置邮箱端口
	      properties.put("mail.smtp.port", 587);
	      //用户密码认证
	      properties.put("mail.smtp.auth", "true");
	      // 获取默认session对象
	      Session session = Session.getDefaultInstance(properties,new Authenticator(){
	        public PasswordAuthentication getPasswordAuthentication()
	        {
	         return new PasswordAuthentication(from, "edkmaocewbxqjbji"); //发件人邮件用户名、密码
	        }
	       });
	 
	      try{
	         // 创建默认的 MimeMessage 对象
	         MimeMessage message = new MimeMessage(session);
	 
	         // Set From: 头部头字段
	         message.setFrom(new InternetAddress(from));
	 
	         // Set To: 头部头字段
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));
	 
	         // Set Subject: 头部头字段
	         message.setSubject(head);
	 
	         // 设置消息体
	         message.setText("This is actual message");
	 
	         // 发送消息
	         Transport.send(message);
	         System.out.println("Sent message successfully....from runoob.com");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	 }
}
