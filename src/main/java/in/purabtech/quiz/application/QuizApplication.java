package in.purabtech.quiz.application;

import in.purabtech.quiz.application.model.Role;
import in.purabtech.quiz.application.model.User;
import in.purabtech.quiz.application.repository.RoleRepository;
import in.purabtech.quiz.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class QuizApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;


	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("starting code..");
		/*User user=new User();
		user.setFirstName("purab");
		user.setLastName("kharat");
		user.setUsername("purabtech");
		user.setPassword("password");
		user.setEmail("test@purabtech.in");
		user.setProfile("default.png");

		List<Role> roles= new ArrayList<>();
		Role role = roleRepository.findByRoleName("USER");
		if(role !=null) {
			roles.add(roleRepository.findByRoleName("USER")); //SET USER ROLE
		} else {
			Role role1=new Role();
			role1.setRoleName("USER");
			roles.add(role1); //SET USER ROLE
		}

		user.setRoles(roles);

		User user1 = userService.createUser(user);

		System.out.println(user1.getUsername());*/

	}
}
