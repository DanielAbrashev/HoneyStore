package com.honeystore;

import com.honeystore.domain.User;
import com.honeystore.domain.security.Role;
import com.honeystore.domain.security.UserRole;
import com.honeystore.service.UserService;
import com.honeystore.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class HoneystoreApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(HoneystoreApplication.class, args);
    }

    public HoneystoreApplication(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... strings) throws Exception {
        User user1 = new User();
        user1.setFirstName("Daniel");
        user1.setLastName("Abrashev");
        user1.setUsername("d");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
        user1.setEmail("dakatest@abv.bg");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setName("ROLE_USER");
        userRoles.add(new UserRole(user1, role1));

        userService.createUser(user1, userRoles);

        User user2 = new User();
        user2.setFirstName("Daniel");
        user2.setLastName("Abrashev");
        user2.setUsername("dakata");
        user2.setPassword(SecurityUtility.passwordEncoder().encode("pa"));
        user2.setEmail("dsadasdsa@abv.bg");
        Set<UserRole> userRoles2 = new HashSet<>();
        Role role2 = new Role();
        role2.setRoleId(1);
        role2.setName("ROLE_USER");
        userRoles2.add(new UserRole(user2, role2));

        userService.createUser(user2, userRoles2);

        User user3 = new User();
        user3.setFirstName("Daniel");
        user3.setLastName("Abrashev");
        user3.setUsername("titi");
        user3.setPassword(SecurityUtility.passwordEncoder().encode("titi"));
        user3.setEmail("ewqeq@abv.bg");
        Set<UserRole> userRoles3 = new HashSet<>();
        Role role3 = new Role();
        role3.setRoleId(1);
        role3.setName("ROLE_USER");
        userRoles3.add(new UserRole(user3, role3));

        userService.createUser(user3, userRoles3);
    }
}
