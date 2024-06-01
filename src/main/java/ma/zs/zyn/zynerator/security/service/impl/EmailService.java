package ma.zs.zyn.zynerator.security.service.impl;


import ma.zs.zyn.zynerator.security.bean.EmailRequest;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    public void sendSimpleMessage(EmailRequest emailRequest){
        String username = "najibilham841@gmail.com";
        String password = "aqmv vzhx dwtl crvn";
        Email email =EmailBuilder.startingBlank()
                .from(username)
                .to(emailRequest.getTo())
                .cc(emailRequest.getCc())
                .bcc(emailRequest.getBcc())
                .withSubject(emailRequest.getSubject())
                .withPlainText("This is a plain text email")
                .withHTMLText(emailRequest.getBody())
                .buildEmail();


        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587)
                .withSMTPServerUsername(username)
                .withSMTPServerPassword(password)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .buildMailer();

        mailer.sendMail(email);

        System.out.println("Le message a été envoyé avec succès!");
    }
}