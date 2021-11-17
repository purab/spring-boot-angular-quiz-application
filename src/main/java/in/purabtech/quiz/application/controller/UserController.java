package in.purabtech.quiz.application.controller;

import in.purabtech.quiz.application.helper.UserNotFoundException;
import in.purabtech.quiz.application.model.User;
import in.purabtech.quiz.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public Object deleteUser(@PathVariable("id") Long id, HttpServletRequest request)
    {
        String username=request.getUserPrincipal().getName();
        System.out.println(request.getUserPrincipal());
        //find logged in user id
        User user = userService.getUserbyUsername(username);
        if(user.getId() != id) {
            userService.deleteUserByID(id);
            return ResponseEntity.ok("user Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN);
        }
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
