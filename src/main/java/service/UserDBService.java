package service;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDBService {
    DBService dbs = new DBService();

    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            ResultSet rs = dbs.select("SELECT * FROM users");
            while (rs.next()) {
                users.add(new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getInt("role")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    public User getByID(int id) {
        User user = null;

        try {
            ResultSet rs = dbs.select("SELECT * FROM users WHERE id=" + id);
            if (rs.next()) {
                user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getInt("role")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {}

        return user;
    }

    public User getByLogin(String login) {
        User user = null;

        try {
            ResultSet rs = dbs.select("SELECT * FROM users WHERE login=\'" + login + "\'");
            if (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getInt("role")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {}

        return user;
    }

    public Integer create(User user) {
        Integer id = null;

        if (getByLogin(user.getLogin()) != null) {
            return id;
        }

        String sql = "INSERT INTO users(name, email, login, password, role) values(\'"
            + user.getName() + "\', \'"
            + user.getEmail() + "\', \'"
            + user.getLogin() + "\', \'"
            + new HashService().hash(user.getPassword()) + "\', "
            + user.getRole()
            + ")";


        if (dbs.insert(sql)) {
            id = getByLogin(user.getLogin()).getId();
        }

        return id;
    }

    public boolean edit(int id, User user) {
        boolean result = true;

        try {
            ResultSet rs = dbs.select("SELECT login FROM users WHERE id!=" + id + " AND login=\'" + user.getLogin() + "\'");
            if (rs.next()) {
                throw new SQLException("User with this login already exists");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NullPointerException e) {
            ;
        }

        try {
            ResultSet rs = dbs.select("SELECT * FROM users WHERE id=" + id);
            if (rs.next()) {
                rs.updateString("name", user.getName());
                rs.updateString("email", user.getEmail());
                rs.updateString("login", user.getLogin());
                rs.updateInt("role", user.getRole());
                rs.updateRow();
            } else throw new SQLException();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return result;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM users WHERE id = " + id;
        return dbs.delete(sql);
    }
}
