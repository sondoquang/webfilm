package com.webmovie.utils;


import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;

/**
 * Lớp tiện ích cung cấp các phương thức gửi email qua Google Mail
 * @author SonDoIT
 * @version 1.0
 */
public class XMailer {
    private static Properties props = new Properties();
    static {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);
    }
    private static Session getSession() {
        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String username = "sondqps41027@gmail.com";
                String password = "ayecxpftqtieqygs";
                return new PasswordAuthentication(username, password);
            }
        });
    }
    /**
     * Gửi email
     * @param mail đối tượng chứa dữ liệu email
     * @exception lỗi gửi mail
     */
    public static void send(MailData mail) {
        Session session = XMailer.getSession();
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail.getFrom()));
            message.setRecipients(RecipientType.TO, mail.getTo());
            message.setSubject(mail.getSubject(), "utf-8");
            message.setText(mail.getBody(), "utf-8", "html");
            message.setReplyTo(message.getFrom());
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Gửi email đơn giản
     * @param to email người nhận
     * @param subject tiêu đề mail
     * @param body nội dung mail
     * @exception lỗi gửi mail
     */
    public static void send(String to, String subject, String body) {
        XMailer.send(new MailData("sondqps41027@gmail.com", to, subject, body));
    }
    /**
     * Mô tả dữ liệu mail
     * @author SonDoIT
     * @version 1.0
     */
    public static class MailData{
        String from = "sondqps41027@gmail.com";
        String to;
        String subject;
        String body;


        public MailData() {
            super();
        }

        public MailData(String from, String to, String subject, String body) {
            super();
            this.from = from;
            this.to = to;
            this.subject = subject;
            this.body = body;
        }
        public String getFrom() {
            return from;
        }
        public void setFrom(String from) {
            this.from = from;
        }
        public String getTo() {
            return to;
        }
        public void setTo(String to) {
            this.to = to;
        }
        public String getSubject() {
            return subject;
        }
        public void setSubject(String subject) {
            this.subject = subject;
        }
        public String getBody() {
            return body;
        }
        public void setBody(String body) {
            this.body = body;
        }


    }
}