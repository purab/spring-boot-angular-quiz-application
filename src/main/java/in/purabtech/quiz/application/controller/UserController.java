package in.purabtech.quiz.application.controller;

import in.purabtech.quiz.application.helper.UserNotFoundException;
import in.purabtech.quiz.application.model.Role;
import in.purabtech.quiz.application.model.User;
import in.purabtech.quiz.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //create user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        return userService.createUser(user);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
            return userService.getUserbyUsername(username);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserByID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) throws Exception {
        final User user1 = userService.UpdateUserById(id,user);
        return ResponseEntity.ok(user1);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserNotFoundException ex) {
        return ResponseEntity.ok("User Not found!");
    }
}
