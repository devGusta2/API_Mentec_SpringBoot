package com.API_MENTEC_SPRINGBOOT.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API_MENTEC_SPRINGBOOT.Model.AlunoFatec;

public interface AlunoRepository extends JpaRepository<AlunoFatec, UUID>{
    
}
