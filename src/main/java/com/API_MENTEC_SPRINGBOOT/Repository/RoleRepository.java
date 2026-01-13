package com.API_MENTEC_SPRINGBOOT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API_MENTEC_SPRINGBOOT.Model.Role;

public interface RoleRepository extends JpaRepository <Role, Long>{
    
}
