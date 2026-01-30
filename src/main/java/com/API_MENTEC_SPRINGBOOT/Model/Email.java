package com.API_MENTEC_SPRINGBOOT.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Email {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //email
    private Long codCheck;
    private Boolean emailCheked;
    private LocalDateTime expiresIn;
    private String userType;
    private String emailOwner;

}
