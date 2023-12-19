package service;

import model.User;
import org.checkerframework.checker.units.qual.A;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
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
        } catch (NullPointerException e) {}

        return users;
    }

    public List<model.admin.User> getForAdminAll() {
        List<model.admin.User> users = new ArrayList<>();

        try {
            ResultSet rs = dbs.select(
                    "SELECT u.*, r.name as roleName " +
                    "FROM users u " +
                    "JOIN role r ON u.role=r.id " +
                    "ORDER BY u.id"
            );
            while (rs.next()) {
                users.add(new model.admin.User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("login"),
                        rs.getInt("role"),
                        rs.getString("roleName")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {}

        return users;
    }

    public List<User> getMasters() {
        List<User> users = new ArrayList<>();

        try {
            ResultSet rs = dbs.select("SELECT * FROM users WHERE role=2 ORDER BY id");
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("login"),
                        rs.getInt("role")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {}

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

        if (getByLogin(user.getLogin()) == null) {
            String sql = "INSERT INTO users(name, email, login, password, role) values(\'"
                    + user.getName() + "\', \'"
                    + user.getEmail() + "\', \'"
                    + user.getLogin() + "\', \'"
                    + user.getPassword() + "\', "
                    + user.getRole()
                    + ")";

            if (dbs.insert(sql) && (user = getByLogin(user.getLogin())) != null) {
                id = user.getId();
            }
        }

        return id;
    }

    public boolean edit(int id, User user) {
        try {
            ResultSet rs = dbs.select("SELECT login FROM users WHERE id!=" + id + " AND login=\'" + user.getLogin() + "\'");
            if (rs.next()) {
                throw new SQLException("Username is already taken");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NullPointerException e) {}

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
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM users WHERE id = " + id;
        return dbs.delete(sql);
    }
}
