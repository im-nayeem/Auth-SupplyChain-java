package com.my_web_app;

import DB.DatabaseConnection;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class Mail {
    private final String host="smtp.gmail.com";
    private Properties pr=System.getProperties();
    private String from;
    private String to;
    private  String pass;


    /**
     * Constructor
     * @param to the mail address to send mail
     * @throws IOException
     */
    public Mail(String to) throws IOException {

        Properties prop = new Properties();
        prop.load(DatabaseConnection.class.getClassLoader().getResourceAsStream("mailConfig.properties"));

        this.from = prop.getProperty("company.email");
        this.to = to;
        this.pass = prop.getProperty("email.pass");

        pr.put("mail.smtp.host",host);
        pr.put("mail.smtp.post","587");
        pr.put("mail.smtp.starttls.enable","true");
        pr.put("mail.smtp.auth","true");
    }

    /**
     * Method to send final mail
     * @param sub the subject of mail
     * @param msg the message of the mail
     */
    public void send(String sub,String msg){

        Session session=Session.getInstance(pr, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from,pass);
            }
        });
        Message message= new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


//    public static void main(String[] args) {
//        Mail mail = null;
//        try {
//            mail = new Mail("hnayeem520@gmail.com");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        mail.send("verification","Your verification code is: 1234");
//    }

}
