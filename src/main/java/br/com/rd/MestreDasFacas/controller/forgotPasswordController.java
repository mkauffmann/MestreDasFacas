package br.com.rd.MestreDasFacas.controller;


import br.com.rd.MestreDasFacas.model.dto.CustomerDTO;
import br.com.rd.MestreDasFacas.model.dto.NewPasswordDTO;
import br.com.rd.MestreDasFacas.model.dto.PasswordRecoveryDTO;
import br.com.rd.MestreDasFacas.model.entity.Customer;
import br.com.rd.MestreDasFacas.service.CustomerNotFoundException;
import br.com.rd.MestreDasFacas.service.CustomerService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

    @PostMapping

         public PasswordRecoveryDTO sendPasswordRecoveryEmail(@RequestBody PasswordRecoveryDTO emailForRecovery){
         String email = emailForRecovery.getEmail();
         String token = RandomString.make(45);
         customerService.findByEmail(email);

         try {
             customerService.updateResetPasswordToken(token, email);
             String resetPasswordLink = "http://localhost:3000/ChangePassword/" + token;

            customerService.sendPasswordRecoveryEmail(email, resetPasswordLink);


         } catch (CustomerNotFoundException e) {
             e.getMessage();
         }

         return emailForRecovery;
     }


     @GetMapping("/reset")
     public CustomerDTO searchByToken(@Param(value = "token")String token){
        return customerService.getByResetPasswordToken(token);
     }


    @PostMapping("/ChangePassword/{token}")

    public NewPasswordDTO resetPassword(@RequestBody NewPasswordDTO passwordDTO, @PathVariable("token")String token){


        String password = passwordDTO.getPassword();
        CustomerDTO customerDTO = customerService.getByResetPasswordToken(token);



        if (customerDTO == null){
            throw new IllegalArgumentException("Token invalido");
        }else{
            customerService.updatePassword(customerDTO, password);

        }
        return passwordDTO;
    }



}
