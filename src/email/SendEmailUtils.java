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
 * �����ʼ�
 * @author xjl
 * 2018-05-02 16:22:44
 */
public class SendEmailUtils {
	/**
	 * �������ʼ���������qq��smtp�������ʼ�
	 * 2018-05-02 16:27:31
	 * @param from ������
	 * @param to �ռ���
	 * @param fromPass ��Ȩ����
	 * @param mailHost �ʼ�������
	 * @param mailPort �ʼ��˿ں�
	 * @param title �ʼ�����
	 * @param content �ʼ�����
	 * @param files ����
	 */
	public static void sendEmail(String from, String to, String fromPass,
			String mailHost, int mailPort, String title, 
			String content, String []files) {
		// ��ȡϵͳ����
	      Properties properties = System.getProperties();
	 
	      // �����ʼ�������
	      properties.setProperty("mail.smtp.host", mailHost);
	      //��������˿�
	      properties.put("mail.smtp.port", mailPort);
	      //�û�������֤
	      properties.put("mail.smtp.auth", "true");
	      // ��ȡĬ��session����
	      Session session = Session.getDefaultInstance(properties,new Authenticator(){
	        public PasswordAuthentication getPasswordAuthentication()
	        {
	         return new PasswordAuthentication(from, fromPass); //�������ʼ��û���������
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
	         message.setSubject(title);
	 
	         
	      // ����������Ϣ
	         Multipart multipart = new MimeMultipart();
	         
	         //������Ϣ����
	         BodyPart  messageBodyPart = new MimeBodyPart();
	         
	         //����
	         messageBodyPart.setText(content);
	         
	      // �����ı���Ϣ����
	         multipart.addBodyPart(messageBodyPart);
	         
	         
	      // ��������
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
	        
	         // ����������Ϣ
	         message.setContent(multipart );
	         
	         // ������Ϣ
	         Transport.send(message);
	         System.out.println("Sent message successfully....from "+from);
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
}
