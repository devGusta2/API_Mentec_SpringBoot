package com.API_MENTEC_SPRINGBOOT.Model;


import com.API_MENTEC_SPRINGBOOT.Enum.Periodo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_aluno_fatec")
public class AlunoFatec extends User {



    private String ra;
    private String curso;
    private Periodo periodo;

    
}
