package com.API_MENTEC_SPRINGBOOT.Controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.API_MENTEC_SPRINGBOOT.DTO.EmailDto;
import com.API_MENTEC_SPRINGBOOT.DTO.VerificacaoDto;
import com.API_MENTEC_SPRINGBOOT.Services.EmailServices;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailServices emailServices;
        
    public EmailController(EmailServices emailServices){
        this.emailServices = emailServices;
    }


    @PostMapping("/check")
    public void enviarVerificação(@RequestBody EmailDto dto){
        emailServices.enviarEmailParaConfirmação(dto);
    }
    @PostMapping("/verify")

    public String verificarCodigo(@RequestBody VerificacaoDto dto){
        return emailServices.verificarCodigo(dto);
    }

}
