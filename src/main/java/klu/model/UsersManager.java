package klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klu.repository.UsersRepository;

@Service
public class UsersManager {

    @Autowired
    private UsersRepository userRepo;

    @Autowired
    private JWTManager jwt;

    public String addUsers(Users user) {
        if (userRepo.validateEmail(user.getEmail()) > 0) {
            return "401::Email ID already exists";
        }
        userRepo.save(user);
        return "200::User registered successfully";
    }

    public String validateCredentials(String email, String password) {
        Users user = userRepo.findByEmailAndPassword(email, password);
        if (user != null) {
            String token = jwt.generateToken(email);
            return "200::" + token + "::" + user.getFullname() + "::" + user.getRole();
        } else {
            return "401::Invalid credentials";
        }
    }

    public String getFullname(String token) {
        String email = jwt.validateToken(token);
        if ("401".equals(email)) {
            return "401::Token expired";
        }
        Users user = userRepo.findById(email).orElse(null);
        return user != null ? user.getFullname() : "401::User not found";
    }

    public Users getUserByEmailAndPassword(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password);
    }
}