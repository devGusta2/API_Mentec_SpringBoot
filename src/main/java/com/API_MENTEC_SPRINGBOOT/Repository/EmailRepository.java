package com.API_MENTEC_SPRINGBOOT.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API_MENTEC_SPRINGBOOT.Model.Email;
import com.API_MENTEC_SPRINGBOOT.Model.User;

public interface EmailRepository extends JpaRepository <Email, Long>{

    Email findByEmailOwner(String emailOwner);

   
}
