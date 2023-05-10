package dat.backend.model.entities;

import java.util.Objects;

public class User {
    private String username;
    private String password;
    private String role;
    private int userId;
    private int orderId;

    public User(int userId, String username) {
    this.userId = userId;
    this.username = username;
    }
    public User(int userId, String username, int orderId) {
        this.userId = userId;
        this.username = username;
        this.orderId = orderId;
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(int userId, String username, String password, String role, int orderId) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.userId = userId;
        this.orderId = orderId;
    }

    public User(int userId, String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword()) &&
                getRole().equals(user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "brugerNavn='" + username + '\'' +
                ", kodeord='" + password + '\'' +
                ", rolle='" + role + '\'' +
                '}';
    }
}
