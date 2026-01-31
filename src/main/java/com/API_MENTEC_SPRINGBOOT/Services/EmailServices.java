package com.API_MENTEC_SPRINGBOOT.Services;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.API_MENTEC_SPRINGBOOT.DTO.EmailDto;
import com.API_MENTEC_SPRINGBOOT.DTO.VerificacaoDto;

import com.API_MENTEC_SPRINGBOOT.Model.Email;


import com.API_MENTEC_SPRINGBOOT.Repository.EmailRepository;
import com.API_MENTEC_SPRINGBOOT.Repository.RoleRepository;
import com.API_MENTEC_SPRINGBOOT.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class EmailServices {

    private final BCryptPasswordEncoder cript;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public EmailServices(BCryptPasswordEncoder cript, UserRepository userRepository, RoleRepository roleRepository) {
        this.cript = cript;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository emailRepository;



    @Value("${spring.mail.username}")
    private String remetente;

    public String enviarEmailParaConfirmação(EmailDto dto) {

        Long codigo = 100000L + (long) new Random().nextInt(900000);
        String emailDestinatario = dto.destinatario();

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(remetente);
            helper.setTo(emailDestinatario);
            helper.setSubject("Seu código de confirmação Mentec");

            String html = gerarHtmlEmail(dto.nome(), codigo);
            helper.setText(html, true);

            ClassPathResource imagem = new ClassPathResource("email/Mascote_Mentec_vermelho.png");

            helper.addInline("mascoteMentec", imagem);

            javaMailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail", e);
        }

        var email = new Email();
        email.setExpiresIn(LocalDateTime.now().plusMinutes(10));
        email.setEmailCheked(false);
        email.setCodCheck(codigo);
        email.setEmailOwner(emailDestinatario);

        email.setNome(dto.nome());
        email.setCpf(dto.cpf());
        email.setSobrenome(dto.sobrenome());
        email.setSenha(cript.encode(dto.senha()));

        if (emailDestinatario.toLowerCase().endsWith("fatec.sp.gov.br")) {
            email.setUserType("Aluno Fatec");

        } else {
            email.setUserType("Comum");
        }

        emailRepository.save(email);

        return "E-mail enviado";
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

        var tempoExp = emailToVerify.getExpiresIn();

        if (LocalDateTime.now().isAfter(tempoExp)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Código expirou!");
        } else {

            if (codigoParaVerificar.equals(dto.codigo())) {

                // email.setNome(dto.nome());
                // email.setCpf(dto.cpf());
                // email.setSobrenome(dto.sobrenome());
                // email.setSenha(cript.encode(dto.senha()));
                com.API_MENTEC_SPRINGBOOT.Model.User user = new com.API_MENTEC_SPRINGBOOT.Model.User();

           
    

                user.setNome(emailToVerify.getNome());
                user.setSobrenome(emailToVerify.getSobrenome());
                user.setCpf(emailToVerify.getCpf());
                user.setCpf(emailToVerify.getCpf());
                user.setSenha(emailToVerify.getSenha());
                user.setEmail(emailToVerify.getEmailOwner());
                user.setActive(true);
                user.setCriadoEm(LocalDateTime.now());
                if ("Aluno Fatec".equals(usuarioFinal)) {
                    var userFatec = roleRepository
                            .findByName(com.API_MENTEC_SPRINGBOOT.Model.Role.Values.ESTUDANTEFATEC.name());
                    user.setRoles(Set.of(userFatec));
                    user.setRole("Estudante Fatec");
                    ;
                    userRepository.save(user);

                } else {
                    var userbasic = roleRepository
                            .findByName(com.API_MENTEC_SPRINGBOOT.Model.Role.Values.BASICO.name());
                    user.setRoles(Set.of(userbasic));
                    user.setRole("Comum");
                    ;
                    userRepository.save(user);
                }
            } else {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Código inválido!");
            }
        }
        return "Código verificado!";
    }

    private String gerarHtmlEmail(String nome, Long codigo) {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                </head>
                <body style="margin:0; padding:0; font-family: Arial, sans-serif;">
                    <table width="100%%" cellpadding="0" cellspacing="0">
                        <tr>
                            <td style="background-color:#7b0d1e; padding:20px; text-align:center;">
                                <img src="cid:mascoteMentec" width="120" alt="Mentec">
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <table width="500" cellpadding="0" cellspacing="0" style="padding:30px;">
                                    <tr>
                                        <td>
                                            <h2 style="text-align:center;">Bem-vindo(a) ao Mentec</h2>

                                            <p>Olá, <strong>%s</strong></p>

                                            <p>
                                                Recebemos uma solicitação para confirmar seu e-mail.<br>
                                                <strong>Seu código de confirmação é:</strong>
                                            </p>

                                            <p style="
                                                text-align:left;
                                                font-size:24px;
                                                font-weight:bold;
                                                color:#7b0d1e;
                                                letter-spacing:2px;
                                            ">
                                                %d
                                            </p>

                                            <p>Esse código é válido por 10 minutos.</p>

                                            <p style="font-size:12px; color:#666;">
                                                Se você não solicitou este cadastro, pode ignorar este e-mail.
                                            </p>

                                            <p style="font-size:12px; margin-top:30px;">
                                                Atenciosamente,<br>
                                                <strong>Equipe Mentec</strong>
                                            </p>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </body>
                </html>
                """.formatted(nome, codigo);
    }

}
