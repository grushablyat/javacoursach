package service;

import com.google.common.hash.Hashing;
import model.User;

import java.nio.charset.StandardCharsets;

public class LoginService {
    public User logIn(String login, String password) {
        User user = new UserDBService().getByLogin(login);
        if (user != null && user.getPassword().equals(hash(password))) {
            return user;
        }

        return null;
    }

    public User signUp(String name, String email, String login, String password, int role) {
        User toBeReturned = null;
        UserDBService udbs = new UserDBService();

        Integer id = udbs.create(new User (name, email, login, hash(password), role));
        if (id != null) {
            toBeReturned = udbs.getByID(id);
        }

        return toBeReturned;
    }

    public String hash(String toBeHashed) {
        return Hashing.sha256().hashString(toBeHashed, StandardCharsets.UTF_8).toString();
    }
}
