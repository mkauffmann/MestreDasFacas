package br.com.rd.MestreDasFacas.service.email.send;

import br.com.rd.MestreDasFacas.service.email.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Mailer {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(Message mensagem) {

        SimpleMailMessage simpleMessage = new SimpleMailMessage();

        simpleMessage.setFrom(mensagem.getRemetente());
        simpleMessage.setTo(mensagem.getDestinatarios()
                .toArray(new String[mensagem.getDestinatarios().size()]));
        simpleMessage.setSubject(mensagem.getAssunto());
        simpleMessage.setText(mensagem.getCorpo());

        javaMailSender.send(simpleMessage);

    }

}
