package pl.mw.model;

import java.util.Objects;

public class User {

    private int id_user;
    private String username;
    private String firstname;
    private String lastname;
    private int phonenumber;
    private String email;
    private String password;
    private String role;


    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId_user() == user.getId_user() &&
                getPhonenumber() == user.getPhonenumber() &&
                getUsername().equals(user.getUsername()) &&
                getFirstname().equals(user.getFirstname()) &&
                getLastname().equals(user.getLastname()) &&
                getEmail().equals(user.getEmail()) &&
                getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_user(), getUsername(), getFirstname(), getLastname(), getPhonenumber(), getEmail(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phonenumber=" + phonenumber +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}