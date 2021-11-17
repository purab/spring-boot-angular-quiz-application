package in.purabtech.quiz.application.repository;

import in.purabtech.quiz.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
