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
public class QuizApplication {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;


	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

}
