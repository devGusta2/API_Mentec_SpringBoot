package com.API_MENTEC_SPRINGBOOT.Services;

import java.time.LocalDateTime;
import java.util.Random;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.API_MENTEC_SPRINGBOOT.DTO.EmailDto;
import com.API_MENTEC_SPRINGBOOT.DTO.VerificacaoDto;
import com.API_MENTEC_SPRINGBOOT.Model.AlunoFatec;
import com.API_MENTEC_SPRINGBOOT.Model.Email;
import com.API_MENTEC_SPRINGBOOT.Repository.AlunoRepository;
import com.API_MENTEC_SPRINGBOOT.Repository.EmailRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

@Service
public class EmailServices {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Value("${spring.mail.username}")
    private String remetente;

    public String enviarEmailParaConfirmação(EmailDto dto) {
    Long codigo = 100000L + (long) new Random().nextInt(900000);


        String assunto = "Seu código de confirmação Mentec";
        String mensagem = """
                Olá!

                Recebemos uma solicitação para confirmar seu e-mail.

                Seu código de confirmação é:
                """ + codigo + """

                Esse código é válido por 10 minutos.
                Se você não solicitou este cadastro, pode ignorar este e-mail.

                Atenciosamente,
                Equipe API Mentec
                """;
        String emailDestinatario = dto.destinatario();

        if (emailDestinatario.toLowerCase().endsWith("fatec.sp.gov.br")) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(dto.destinatario());
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            javaMailSender.send(simpleMailMessage);

            var email = new Email();
            email.setExpiresIn(LocalDateTime.now().plusMinutes(10));
            email.setUserType("Aluno Fatec");
            email.setEmailCheked(false);
            email.setCodCheck(codigo);
            email.setEmailOwner(emailDestinatario);
            emailRepository.save(email);

            return "E-mail enviado";
        } else {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(dto.destinatario());
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            javaMailSender.send(simpleMailMessage);

            var email = new Email();
            email.setExpiresIn(LocalDateTime.now().plusMinutes(10));
            email.setUserType("Comum");
            email.setEmailCheked(false);
            email.setCodCheck(codigo);
            email.setEmailOwner(emailDestinatario);
            emailRepository.save(email);
            return "E-mail enviado";
        }

    }

    public String verificarCodigo(VerificacaoDto dto) {
        var emailToVerify = emailRepository.findByEmailOwner(dto.email());
        if (emailToVerify == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email não encontrado!");
        }
        var usuarioFinal = emailToVerify.getUserType();
        var codigoParaVerificar = emailToVerify.getCodCheck();
        if (emailToVerify.getExpiresIn().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código expirado!");
        }

        if (codigoParaVerificar.equals(dto.codigo())) {
            if ("Aluno Fatec".equals(usuarioFinal)) {
                var aluno = new AlunoFatec();
                alunoRepository.save(aluno);
            } else {

            }
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Código inválido!");
        }
        return "Código verificado!";
    }

}
