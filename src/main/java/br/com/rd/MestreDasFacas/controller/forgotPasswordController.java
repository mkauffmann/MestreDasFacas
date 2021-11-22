package br.com.rd.MestreDasFacas.controller;


import br.com.rd.MestreDasFacas.model.entity.Customer;
import br.com.rd.MestreDasFacas.service.CustomerNotFoundException;
import br.com.rd.MestreDasFacas.service.CustomerService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
@RestController
public class forgotPasswordController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private JavaMailSender mailSender;



    @GetMapping("/forgotPassword")

    public String showForgotPasswordForm(Model model){

        model.addAttribute("pageTitle", "Forgot Password");
        return "ForgotPassword";

    }

    @PostMapping("/forgotPassword")
    public String processForgotPasswordForm(HttpServletRequest request , Model model){
        String email = request.getParameter("email");
        String token = RandomString.make(45);

        try {
            customerService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;

            sendEmail(email, resetPasswordLink);


            model.addAttribute("message", "O link de recuperação foi enviado para seu email ");

        } catch (CustomerNotFoundException ex){
           model.addAttribute("error", ex.getMessage());
        }catch (UnsupportedEncodingException | MessagingException e ){
            model.addAttribute("error", "Error while sending email.");
        }

        model.addAttribute("pageTitle", "Forgot Password");
        return "ForgotPassword";
    }

    private void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);


        helper.setFrom("mestredasfacas2021@gmail.com", "Suporte Mestre das Facas");
        helper.setTo(email);

        String subject = "Resetar senha da sua conta";

        String content = "<p> Olá, </p> "
                + "<p>Você solicitou o reset da sua senha.</p>"
                + "<p>Clique no link abaixo para resetar sua senha: </p> "
                + "<p> <b> <a href=\"" + resetPasswordLink + "\" >Resetar minha senha</a><b></p>" +
                "<p>Caso não tenha sido você que fez esta solicitação por favor ignore este email </p>";
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    @GetMapping("/reset_password")

    public String showResetPasswordForm(@Param(value = "token") String token, Model model)

    {
        Customer customer = customerService.get(token);
        if (customer == null){
            model.addAttribute("title", "Reset sua senha");
            model.addAttribute("message", "invalid Token");
            return "message";
        }

        model.addAttribute("token", token);
        model.addAttribute("pageTitle", "Reset sua Password");
        return "ForgotPassword";
    }

    @PostMapping("/reset_password")

    public String processResetPassword(HttpServletRequest request, Model model){

        String token = request.getParameter("token");
        String password = request.getParameter("password");

        Customer customer = customerService.get(token);

        if (customer == null){
            model.addAttribute("title", "Reset sua senha");
            model.addAttribute("message", "invalid Token");
            return "message";
        }else{
            customerService.updatePassword(customer, password);
            model.addAttribute("message", "Você resetou sua senha com sucesso");
        }
        return "message";
    }



}
