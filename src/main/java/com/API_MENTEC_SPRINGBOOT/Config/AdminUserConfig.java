package com.API_MENTEC_SPRINGBOOT.Config;

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
        // 1️⃣ Garantir que a role ADMIN exista no banco
        Role roleAdmin = Optional.ofNullable(roleRepository.findByName("ADMIN"))
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName("ADMIN");
                    return roleRepository.save(newRole);
                });

        // 2️⃣ Verificar se o admin já existe
        Optional<User> userAdmin = userRepository.findByEmail("fatec.admin");

        userAdmin.ifPresentOrElse(
                admin -> System.out.println("Admin já existe"),
                () -> {
                    // 3️⃣ Criar o admin se não existir
                    User user = new User();
                    user.setEmail("fatec.admin");
                    user.setSenha(bCryptPasswordEncoder.encode("123"));
                    user.setRole("ADMIN"); // Tipo de usuário rápido
                    user.setRoles(Set.of(roleAdmin)); // Roles para segurança
                    user.setActive(true); // Ativar o usuário
                    userRepository.save(user);
                    System.out.println("Admin criado com sucesso!");
                }
        );
    }
}
