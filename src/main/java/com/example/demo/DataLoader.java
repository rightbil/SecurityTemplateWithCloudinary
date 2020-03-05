package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
       /* roleRepository.save(new Role("USER"));*/
        roleRepository.save(new Role("ADMIN"));
        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");
/*
        User user = new User("user@user.com", "user", "User", "User", true, "user");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);*/

        User user = new User("admin@admin.com", "admin", "Admin", "Admin", true, "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

    }
}
