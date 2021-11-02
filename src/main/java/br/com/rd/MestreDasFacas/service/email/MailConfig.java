package br.com.rd.MestreDasFacas.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:src/main/java/br/com/rd/MestreDasFacas/service/email/send/mail.properties") // ou classpath
public class MailConfig {

    @Autowired
    private Environment env;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mainSender = new JavaMailSenderImpl();

        mainSender.setHost(env.getProperty("mail.smtp.host"));
        mainSender.setPort(env.getProperty("mail.smtp.port", Integer.class));
        mainSender.setUsername(env.getProperty("mail.smtp.username"));
        mainSender.setPassword(env.getProperty("mail.smtp.password"));

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.connectiontimeout", 10000);

        mainSender.setJavaMailProperties(props);

        return mainSender;
    }

}
