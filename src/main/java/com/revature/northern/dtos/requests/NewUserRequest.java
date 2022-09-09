package com.revature.northern.dtos.requests;

//This is like register - User registration using username, password1 & password2
//Then validated in  UserService to check if  credentials are valid
public class NewUserRequest {
    //Partial registration - no need for the entire User model

    private String username;
    private String email;
    private String password1;
    private String password2;
    private String given_name;
    private String surname;

/* use this NewUserRequest model-- when registering new user in POSTMAN and use these field in UserDAO and UserService classes*/

    public NewUserRequest() {
    }

    public NewUserRequest(String username, String email, String password1, String password2, String given_name, String surname) {
        this.username = username;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.given_name = given_name;
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                ", given_name='" + given_name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
