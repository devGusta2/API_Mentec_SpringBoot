package com.API_MENTEC_SPRINGBOOT.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "emails")
public class Email {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //email
    private Long codCheck;
    private Boolean emailCheked;
    private LocalDateTime expiresIn;

    @Column(unique = true)
    private String emailOwner;
    private String nome;
    private String sobrenome;
    private String senha;
    private String cpf;
    private String userType;

}
