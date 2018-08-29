package email;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 发送邮件
 * @author xjl
 * 2018-05-02 16:22:44
 */
public class SendEmailUtils {
	/**
	 * 第三方邮件服务器（qq、smtp）发送邮件
	 * 2018-05-02 16:27:31
	 * @param from 发件人
	 * @param to 收件人
	 * @param fromPass 授权密码
	 * @param mailHost 邮件服务器
	 * @param mailPort 邮件端口号
	 * @param title 邮件标题
	 * @param content 邮件内容
	 * @param files 附件
	 */
	public static void sendEmail(String from, String to, String fromPass,
			String mailHost, int mailPort, String title, 
			String content, String []files) {
		// 获取系统属性
	      Properties properties = System.getProperties();
	 
	      // 设置邮件服务器
	      properties.setProperty("mail.smtp.host", mailHost);
	      //设置邮箱端口
	      properties.put("mail.smtp.port", mailPort);
	      //用户密码认证
	      properties.put("mail.smtp.auth", "true");
	      // 获取默认session对象
	      Session session = Session.getDefaultInstance(properties,new Authenticator(){
	        public PasswordAuthentication getPasswordAuthentication()
	        {
	         return new PasswordAuthentication(from, fromPass); //发件人邮件用户名、密码
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
	         message.setSubject(title);
	 
	         
	      // 创建多重消息
	         Multipart multipart = new MimeMultipart();
	         
	         //创建消息部分
	         BodyPart  messageBodyPart = new MimeBodyPart();
	         
	         //内容
	         messageBodyPart.setText(content);
	         
	      // 设置文本消息部分
	         multipart.addBodyPart(messageBodyPart);
	         
	         
	      // 附件部分
	         for (int i = 0; i < files.length; i++) {
	        	 String filename = files[i];
	        	 if(!new File(filename).exists()) {
	        		 System.out.println(filename + " is not exists");
	        		 continue;
	        	 }
	        	 messageBodyPart = new MimeBodyPart();
		         DataSource source = new FileDataSource(filename);
		         messageBodyPart.setDataHandler(new DataHandler(source));
		         messageBodyPart.setFileName(filename);
		         multipart.addBodyPart(messageBodyPart);
			}
	        
	         // 发送完整消息
	         message.setContent(multipart );
	         
	         // 发送消息
	         Transport.send(message);
	         System.out.println("Sent message successfully....from "+from);
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
}
