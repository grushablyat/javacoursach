package model.admin;

public class User extends model.User {
    private String roleName;

    public User(int id, String name, String email, String login, int role, String roleName) {
        super(id, name, email, login, role);
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
