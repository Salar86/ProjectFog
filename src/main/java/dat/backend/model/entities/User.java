package dat.backend.model.entities;

import java.util.Objects;

public class User {
    private String email;
    private String password;
    private String role;
    private String fullname;
    private String phonenumber;
    private int userId;
    private int orderId;


    public User(int userId, String role, String fullname, String email, String password, String phonenumber) {
        this.userId = userId;
        this.role = role;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
    }

    public User(String email, String password, String role, int userId) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.userId = userId;
    }
    public User(int userId, String role, String fullname, String email, String password, String phonenumber, int orderId) {
        this.userId = userId;
        this.role = role;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public void getOrderId(int orderId){
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword()) &&
                getRole().equals(user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), getRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", userId=" + userId +
                ", orderId=" + orderId +
                '}';
    }
}
