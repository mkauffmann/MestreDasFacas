package br.com.rd.MestreDasFacas;

import br.com.rd.MestreDasFacas.service.email.message.Message;
import br.com.rd.MestreDasFacas.service.email.send.Mailer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class MestreDasFacasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MestreDasFacasApplication.class, args);

		// API EMAIL:

		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(
				MestreDasFacasApplication.class.getPackage().getName());

		Mailer mailer = appContext.getBean(Mailer.class);
		mailer.send(new Message("Joao Victor <jv.canada1@gmail.com> ", Arrays.asList("Panda Revolts <fazeroquen@gmail.com> "), "Teste de Email Spring", "Olá Jovem! Envio deu certo :)"));

		appContext.close();

		System.out.println("App Email encerrado!");

	}



}
