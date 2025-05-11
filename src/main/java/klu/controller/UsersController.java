package klu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import klu.model.JWTManager;
import klu.model.Users;
import klu.model.UsersManager;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UsersController {

    @Autowired
    private UsersManager usersManager;

    @Autowired
    private JWTManager jwtManager;

    @PostMapping("/signup")
    public String signup(@RequestBody Users user) {
        return usersManager.addUsers(user);
    }

    @PostMapping("/signin")
    public String signin(@RequestBody Users user) {
        return usersManager.validateCredentials(user.getEmail(), user.getPassword());
    }

    @PostMapping("/getfullname")
    public String getFullname(@RequestBody Map<String, String> data) {
        String token = data.get("token");
        if (token == null || token.isEmpty() || jwtManager.validateToken(token).equals("401")) {
            return "401::Unauthorized";
        }
        return usersManager.getFullname(token);
    }

} 