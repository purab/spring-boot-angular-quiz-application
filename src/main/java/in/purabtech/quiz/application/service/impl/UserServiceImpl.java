package in.purabtech.quiz.application.service.impl;

import in.purabtech.quiz.application.model.Role;
import in.purabtech.quiz.application.model.User;
import in.purabtech.quiz.application.repository.RoleRepository;
import in.purabtech.quiz.application.repository.UserRepository;
import in.purabtech.quiz.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user) throws Exception {
        User local = userRepository.findByUsername(user.getUsername());
        if(local !=null){
            System.out.println("User already exits!");
            throw new Exception("User already present");
        } else {
            //create user
            User user1=new User();
            user1.setFirstName(user.getFirstName());
            user1.setLastName(user.getLastName());
            user1.setUsername(user.getUsername());
            user1.setPassword(user.getPassword());
            user1.setEmail(user.getEmail());
            user1.setProfile(user.getProfile());

            List<Role> roles= new ArrayList<>();
            Role role = roleRepository.findByRoleName("USER");
            if(role !=null) {
                roles.add(roleRepository.findByRoleName("USER")); //SET USER ROLE
            } else {
                Role role1=new Role();
                role1.setRoleName("USER");
                roles.add(role1); //SET USER ROLE
            }
            user1.setRoles(roles);

            local = userRepository.save(user1);
        }
        return local;
    }

    @Override
    public User getUserbyUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUserByID(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User UpdateUserById(Long id, User user) throws Exception {
        User user1 = userRepository.findById(id)
                .orElseThrow(()-> new Exception("User not found for this id ::"+id));

        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setEmail(user.getEmail());
        user1.setProfile(user.getProfile());

        return userRepository.save(user1);
    }
}
