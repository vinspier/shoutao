package com.vinspier.utils;

import constant.Constant;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Properties;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
public class MailUtil {
    public static void sendMail(String userName,String email,String emailMessage) throws Exception{
        try {
            //创建一个程序与邮件服务器回话对象 Session
            Properties properties = new Properties();
            //设置发送的协议
            properties.setProperty("mail.transport.protocol", Constant.MAIL_TRANSPORT_PROTOCOL);
            //设置发送邮件的服务器
            properties.setProperty("mail.smtp.host",Constant.MAIL_SMTP_HOST);
            properties.setProperty("mail.smtp.auth",Constant.MAIL_SMTP_AUTH);//指定验证为true;
            properties.setProperty("mail.smtp.port","25");

/*            //创建验证器
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    //设置发送人的帐号和密码
                    return new PasswordAuthentication("17767160232@163.com","Ff13073886467");
                }
            };*/

            Session session = Session.getInstance(properties);
            MimeMessage message = new MimeMessage(session);

            /**设置message信息*/
            message.setFrom(new InternetAddress(Constant.MAIL_SENDER,"秀淘商城平台"));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(email,userName+"用户"));
            message.setSubject("用户激活","UTF-8");
            message.setContent(emailMessage,"text/html;charset=UTF-8");
            message.setSentDate(Calendar.getInstance().getTime());
            message.saveChanges();


            Transport transport = session.getTransport();
            transport.connect(Constant.MAIL_SENDER,Constant.MAIL_SENDER_PASSWORD);
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
