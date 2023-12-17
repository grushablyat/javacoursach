package service;

import com.google.common.hash.Hashing;
import model.User;

import java.nio.charset.StandardCharsets;

public class LoginService {
    public Integer logIn(String login, String password) {
        Integer id = null;
        User user = new UserDBService().getByLogin(login);
        if (user != null && user.getPassword().equals(hash(password))) {
            id = user.getId();
        }

        return id;
    }

    public Integer signUp(String name, String email, String login, String password, int role) {
        Integer id = null;
        UserDBService udbs = new UserDBService();

        id = udbs.create(new User (name, email, login, hash(password), role));

        return id;
    }

    public String hash(String toBeHashed) {
        return Hashing.sha256().hashString(toBeHashed, StandardCharsets.UTF_8).toString();
    }
}
