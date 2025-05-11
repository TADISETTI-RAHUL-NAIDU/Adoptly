package klu.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;

import klu.repository.MenusRepository;
import klu.repository.UsersRepository;

@Service
public class MenusManager {

    @Autowired
    private MenusRepository menuRepo;

    @Autowired
    private JWTManager jwt;

    @Autowired
    private UsersRepository userRepo;

    public String getMenus() {
        return new GsonBuilder().create().toJson(menuRepo.findAll());
    }

    public String getMenusByRole(String token) {
        String email = jwt.validateToken(token);
        if ("401".equals(email)) {
            return "401::Invalid token";
        }

        Users user = userRepo.findById(email).orElse(null);
        if (user == null) {
            return "401::User not found";
        }

        List<Menus> menuItems = menuRepo.findbyRole(user.getRole());
        return new GsonBuilder().create().toJson(menuItems);
    }
}
