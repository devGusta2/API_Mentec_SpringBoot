package com.API_MENTEC_SPRINGBOOT.Config;

import java.time.LocalDateTime;
import java.util.Optional;
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
public class AdminUserConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminUserConfig(
            UserRepository userRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // 1️⃣ Criar TODAS as roles necessárias (Garante que não haverá NPE)
        Role roleAdmin = createRoleIfNotFound("ADMIN");
        createRoleIfNotFound("ESTUDANTEFATEC");
        createRoleIfNotFound("BASICO");
        createRoleIfNotFound("PROFESSOR");

        // 2️⃣ Verificar se o admin já existe
        Optional<User> userAdmin = userRepository.findByEmail("fatec.admin");

        if (userAdmin.isEmpty()) {
            User user = new User();
            user.setEmail("fatec.admin");
            user.setSenha(bCryptPasswordEncoder.encode("123"));
            user.setRole("ADMIN");
            user.setRoles(Set.of(roleAdmin));
            user.setActive(true);
            user.setCriadoEm(LocalDateTime.now());
            userRepository.save(user);
            System.out.println("Admin criado com sucesso!");
        }
    }

    // Método auxiliar para evitar repetição
    private Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            Role newRole = new Role();
            newRole.setName(name);
            return roleRepository.save(newRole);
        }
        return role;
    }
}
