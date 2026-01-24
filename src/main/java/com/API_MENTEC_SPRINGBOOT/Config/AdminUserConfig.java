package com.API_MENTEC_SPRINGBOOT.Config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.API_MENTEC_SPRINGBOOT.Model.Role;
import com.API_MENTEC_SPRINGBOOT.Model.User;
import com.API_MENTEC_SPRINGBOOT.Repository.RoleRepository;
import com.API_MENTEC_SPRINGBOOT.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Configuration
public class AdminUserConfig implements CommandLineRunner{
    
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;


    public AdminUserConfig(
        UserRepository userRepository,
        BCryptPasswordEncoder bCryptPasswordEncoder,
        RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository =roleRepository ;
    }


    
    @Override
    @Transactional
    public void run(String...args) throws Exception{
        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        var userAdmin = userRepository.findByEmail("admin");

        userAdmin.ifPresentOrElse(
            admin -> System.out.println("Admin já existe"),
            ()->{
                var user = new User();
                user.setEmail("fatec.admin");
                user.setSenha(bCryptPasswordEncoder.encode("123"));
                user.setRoles(Set.of(roleAdmin));
                userRepository.save(user);
            }
        );
    }
    
}
