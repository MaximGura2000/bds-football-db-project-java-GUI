package org.but.feec.footballdb.api;

import java.util.Arrays;

public class UserCreateView {

    private String email;
    private String firstname;
    private String username;
    private String surname;
    private char[] password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserCreateView{" +
                "email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", password=" + Arrays.toString(password) +
                '}';
    }
}
