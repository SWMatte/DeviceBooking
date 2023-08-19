package Device.deviceProject.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendEmailService {

    @Autowired
    private final JavaMailSender javaMailSender;
    public SendEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(List<String> toEmail, String subject, String message){
        for (String e : toEmail) {

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(e);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);
            mailMessage.setFrom("peirettimatteo1@gmail.com");
            javaMailSender.send(mailMessage);
        }
    }

    @Async
    public void sendSingleEmail(String toEmail, String subject, String message){

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(toEmail);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);
            mailMessage.setFrom("peirettimatteo1@gmail.com");
            javaMailSender.send(mailMessage);

    }

}