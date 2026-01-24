package com.API_MENTEC_SPRINGBOOT.Model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.API_MENTEC_SPRINGBOOT.DTO.LoginRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "tb_user")
@Getter
@Setter
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;

    @Column(unique = true)
    private String cpf;

    private boolean isActive;

    private LocalDateTime criadoEm;
    


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "tb_user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;


    public enum Values{
        ESTUDANTEFATEC(2L),
        BASICO(1L),
        PROFESSOR(3L),
        ADMIN(4L);

        Long roleId;

        Values(Long roleId){
            this.roleId = roleId;
        }
    }


    public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder){

        return passwordEncoder.matches(loginRequest.senha(), this.senha);

      
    }

}
