package model;

public class User {
    private int id;
    private String name;
    private String email;
    private String login;
    private String password;
    private int role;

    public enum Role {
        ADMIN(1),
        MASTER(2),
        CLIENT(3);

        private final int role;

        Role(int i) {
            role = i;
        }

        public int getRole() {
            return role;
        }
    }

    public User(String name, String email, String login, String password, int role) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String name, String email, String login, String password) {
        this(name, email, login, password, Role.CLIENT.getRole());
    }

    public User(int id, String name, String email, String login, String password, int role) {
        this(name, email, login, password, role);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
