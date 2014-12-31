package com.security.mvc.util;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {
	
	public static boolean sendMail(String tomail, 
			String title, String content,String fromsmtp,String fromuser,String frompassword,String frommail) {	
		try {
			Properties props = new Properties();// 用javax.mail.Properties类来创建一个session对象
			props.put("mail.smtp.host", fromsmtp);// 存储发送邮件服务器的信息
			props.put("mail.smtp.auth", "true");// 同时通过验证
			Session s = Session.getInstance(props);// 根据属性新建一个邮件会话
			s.setDebug(true);
			MimeMessage message = new MimeMessage(s);// 由邮件会话新建一个消息对象

			// 设置邮件
			InternetAddress from = new InternetAddress(frommail);// 需填写自己的电子邮件地址
			message.setFrom(from);// 设置发件人
			
			String[] mails = tomail.split(";");

			if (mails.length > 1) {
				// 设置多个收件人
				Address[] to = new Address[mails.length]; 
				
				for(int i=0;i<mails.length;i++)
				{
					InternetAddress address = new InternetAddress(mails[i]);
					to[i]=address;
				}
				message.setRecipients(Message.RecipientType.TO, to);
			}
			else
			{
				// 设置单个收件人
				InternetAddress to = new InternetAddress(tomail);
				message.setRecipient(Message.RecipientType.TO, to);
			}
			
			message.setSubject(title);// 设置标题
			// message.setText("ceshiyixiama1");//设置内容
			message.setSentDate(new Date());// 设置发信时间

			// 给消息对象设置内容
			BodyPart mdp = new MimeBodyPart();// 新建一个存放信件内容的BodyPart对象
			// mdp.setContent(content,"text/html;charset=gb2312");//设置BodyPart对象的内容和格式
			mdp.setContent(content, "text/plain;charset=gb2312");

			Multipart mm = new MimeMultipart();// 新建一个Multipart来存放bodypart对象
			mm.addBodyPart(mdp);// 将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
			message.setContent(mm);// 把mm作为消息对象的内容

			// 发送邮件
			message.saveChanges();// 存储邮件信息
			Transport transport = s.getTransport("smtp");// 用Javamail
															// Session对象的getTransport方法来初始化javax.mail.Transport类,并申明协议smtp
			transport.connect(fromsmtp, fromuser, frompassword);// 以SMTP方式登陆邮箱,需填写自己的邮箱smtp地址和邮箱用户名密码
			transport.sendMessage(message, message.getAllRecipients());// 发送邮件
			transport.close();// 关闭

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
