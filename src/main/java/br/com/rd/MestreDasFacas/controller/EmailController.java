package br.com.rd.MestreDasFacas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/email-send")
    public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Olá! Esse é um teste de Email");
        message.setSubject("Confirmação de cadastro!");
        message.setTo("fazeroquen@gmail.com");
        message.setFrom("fazeroquen@gmail.com");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e){
            e.printStackTrace();
            return "Falha ao enviar o email!";
        }


    }


}
