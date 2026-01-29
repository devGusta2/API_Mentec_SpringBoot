package com.API_MENTEC_SPRINGBOOT.Services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;


@Service
public class EmailServices {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public String enviarEmailTexto(String destinatario, String assunto, String mensagem){
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            javaMailSender.send(simpleMailMessage);
            return "E-mail enviado";
        
            
        } catch (Exception e) {
            return "Falha ao enviar E-mail " + e.getLocalizedMessage();
        }
    }

    public String gerarCodigo(){
        return String.valueOf(100000 + new Random().nextInt());
    }
    public String enviarEmailParaConfirmação(String destinatario, String assunto, String mensagem){
     try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            javaMailSender.send(simpleMailMessage);
            return "E-mail enviado";
        
            
        } catch (Exception e) {
            return "Falha ao enviar E-mail " + e.getLocalizedMessage();
        }
    }
}
