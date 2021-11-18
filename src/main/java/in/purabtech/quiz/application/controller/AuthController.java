package in.purabtech.quiz.application.controller;

import in.purabtech.quiz.application.config.JwtUtils;
import in.purabtech.quiz.application.helper.UserNotFoundException;
import in.purabtech.quiz.application.model.JwtRequest;
import in.purabtech.quiz.application.model.JwtResponse;
import in.purabtech.quiz.application.model.User;
import in.purabtech.quiz.application.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    //generate token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            this.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User Not found..");
        }
        //user is authenticated
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            e.printStackTrace();
            throw new Exception("user is disabled" + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Bad credentails"+e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return (User) userDetailsService.loadUserByUsername(principal.getName());
    }
}
