package com.API_MENTEC_SPRINGBOOT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API_MENTEC_SPRINGBOOT.Model.Email;

public interface EmailRepository extends JpaRepository <Email, Long>{

    Email findByEmailOwner(String emailOwner);

    
}
