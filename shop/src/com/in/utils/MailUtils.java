package com.in.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.Créer un objet de session entre le code et le serveur de messagerie

		Properties props = new Properties();
		//le protocole d'envoi
		props.setProperty("mail.transport.protocol", "SMTP");
		
		//Configurer le serveur pour l'envoi de courrier
		props.setProperty("mail.host", "smtp.qq.com");
		props.setProperty("mail.smtp.auth", "true");

		// Créer un validateur
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//le mot de passe du compte de l'expéditeur
				return new PasswordAuthentication("", "");
			}
		};
		Session session = Session.getInstance(props, auth);

		// 2.Créer un message, qui est le contenu de l'e-mail
		Message message = new MimeMessage(session);

		//l'expéditeur
		message.setFrom(new InternetAddress(""));

		//la méthode d'envoi et le destinataire
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 

		//le sujet
		message.setSubject("Activiation");
		 
		//contenu
		message.setContent(emailMsg, "text/html;charset=utf-8");

		// 3.envoyer
		Transport.send(message);
	}
}
