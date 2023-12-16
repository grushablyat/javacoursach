package service;

import model.User;

public class LoginService {
    public Integer logIn(String login, String password) {
        Integer id = null;
        User user = new UserDBService().getByLogin(login);
        if (user != null && user.getPassword().equals(new HashService().hash(password))) {
            id = new UserDBService().getByLogin(login).getId();
        }

        return id;
    }

    public Integer signUp(String name, String email, String login, String password) {
        Integer id = null;
        UserDBService udbs = new UserDBService();

        id = udbs.create(new User (name, email, login, password));

        return id;
    }
}
