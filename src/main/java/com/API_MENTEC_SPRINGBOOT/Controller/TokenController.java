package com.API_MENTEC_SPRINGBOOT.Controller;

import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    private final JwtEncoder jwtEncoder;

    public TokenController(JwtEncoder jwtEncoder){
        this.jwtEncoder = jwtEncoder;
    }

}
