package com.banvenez.ast.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    public static void enviarCorreo(String destinatario, String asunto, String cuerpo) {

        final String remitente = "lcasas1604@gmail.com";
        final String clave = "qxzk aezs bebl xuib ";

        System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        System.setProperty("https.protocols", "TLSv1.2");


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setContent(cuerpo, "text/html; charset=utf-8");

            Transport.send(mensaje);
            System.out.println("Correo enviado exitosamente.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
