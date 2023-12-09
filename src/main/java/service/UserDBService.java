package service;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDBService {
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            ResultSet rs = new DBService().select("SELECT * FROM users");
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
            ResultSet rs = new DBService().select("SELECT * FROM users WHERE id=" + id);
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
        }

        return user;
    }

    public User getByLogin(String login) {
        User user = null;

        try {
            ResultSet rs = new DBService().select("SELECT * FROM users WHERE login=" + login);
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
        }

        return user;
    }

    public boolean create(User user) {
        try {
            ResultSet rs = new DBService().select("SELECT login FROM users WHERE login=" + user.getLogin());
            if (rs.next()) {
                throw new SQLException("User with this login already exists");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        String sql = "INSERT INTO users(name, email, login, password, role) values(\'"
            + user.getName() + "\', \'"
            + user.getEmail() + "\', \'"
            + user.getLogin() + "\', \'"
            + new HashService().hash(user.getPassword()) + "\', "
            + user.getRole()
            + ")";

        return new DBService().insert(sql);
    }

    public boolean edit(int id, User user) {
        boolean result = true;

        try {
            ResultSet rs = new DBService().select("SELECT login FROM users WHERE id!=" + id + " AND login=\'" + user.getLogin() + "\'");
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
            ResultSet rs = new DBService().select("SELECT * FROM users WHERE id=" + id);
            if (rs.next()) {
                rs.updateString("name", user.getName());
                rs.updateString("email", user.getEmail());
                rs.updateString("login", user.getLogin());
                rs.updateString("password", new HashService().hash(user.getPassword()));
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
        return new DBService().delete(sql);
    }
}
