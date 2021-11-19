package in.purabtech.quiz.application.helper;

import in.purabtech.quiz.application.model.Role;
import in.purabtech.quiz.application.model.User;
import in.purabtech.quiz.application.repository.RoleRepository;
import in.purabtech.quiz.application.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        createRoleIfNotFound("ADMIN");
        createRoleIfNotFound("USER");

        User tempadmin = userRepository.findByUsername("admin");
        if(tempadmin==null) {
            //admin user
            Role adminRole = roleRepository.findByRoleName("ADMIN");
            User user = new User();
            user.setFirstName("admin");
            user.setLastName("Test");
            user.setPassword(passwordEncoder.encode("password"));
            user.setEmail("admin@admin.com");
            user.setRoles(Arrays.asList(adminRole));
            user.setEnabled(true);
            user.setUsername("admin");
            user.setProfile("default.png");
            userRepository.save(user);
        }
        User tempuser = userRepository.findByUsername("user");
        if(tempuser==null) {
            // normal user
            Role adminRole2 = roleRepository.findByRoleName("USER");
            User user2 = new User();
            user2.setFirstName("user");
            user2.setLastName("Test");
            user2.setPassword(passwordEncoder.encode("password"));
            user2.setEmail("user@user.com");
            user2.setRoles(Arrays.asList(adminRole2));
            user2.setEnabled(true);
            user2.setUsername("user");
            user2.setProfile("default.png");
            userRepository.save(user2);
        }
        alreadySetup = true;
    }

    @Transactional
    Role createRoleIfNotFound(String name) {

        Role role = roleRepository.findByRoleName(name);
        if (role == null) {
            role = new Role();
            role.setRoleName(name);
            roleRepository.save(role);
        }
        return role;
    }
}
