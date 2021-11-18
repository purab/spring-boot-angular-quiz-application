package in.purabtech.quiz.application.service;

import in.purabtech.quiz.application.model.User;

import java.util.Optional;

public interface UserService {
    //create user
    public User createUser(User user) throws Exception;

    //get user by username
    public User getUserbyUsername(String username);

    public void deleteUserByID(Long id);

    public User UpdateUserById(Long id, User user) throws Exception;

    Optional<User> findById(Long id);
}
