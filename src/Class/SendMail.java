//Resources: https://youtu.be/BD9NukSUrqo
package Class;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
    public static void sendMail(String recepient, String username, int level, int score) throws Exception{
        
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
        String myAccountEmail="findtheleast@gmail.com";
        String password="Findtheleast123";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message= prepareMessage(session, myAccountEmail, recepient, username, level, score);
        
        Transport.send(message);
    }
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String username,int level, int score){
        try {
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Fint The Least - Level "+level+" Score Notification");
            message.setText("Hey "+username+". You have scored "+score+" point in level "+level);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

      

