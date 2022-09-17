package com.revature.northern.services;

import com.revature.northern.daos.UserDAO;
import com.revature.northern.dtos.requests.LoginRequest;
import com.revature.northern.dtos.requests.NewUserRequest;
import com.revature.northern.dtos.responses.Principal;
import com.revature.northern.models.User;
import com.revature.northern.utils.custom_exceptions.AuthenticationException;
import com.revature.northern.utils.custom_exceptions.InvalidRequestException;
import com.revature.northern.utils.custom_exceptions.ResourceConflictException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.UUID;

public class UserService {

    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    //User Registering method -- register method, now takes NewUserRequest as a parameter
    //Similar to UserService model validation concept, but this time is Servlet validation
    public User register(NewUserRequest request) {
        User user = null;
//        System.out.println("inside register");
        if (isValidUsername(request.getUsername())) {

            if (!isDuplicateUsername(request.getUsername())) {
                if (isValidPassword(request.getPassword1())) {
                    if (isSamePassword(request.getPassword1(), request.getPassword2())) {
                        user = new User();
                        user.setUser_id(UUID.randomUUID().toString());
                        user.setUsername(request.getUsername());
                        user.setEmail(request.getEmail());
                        user.setPassword(BCrypt.hashpw(request.getPassword1(), BCrypt.gensalt()));
                        user.setGiven_name(request.getGiven_name());
                        user.setSurname(request.getSurname());
                        user.setIs_active(true);
                        user.setRole_id("001");
                        userDAO.save(user);
                    }
                }
            }
        }

        return user;
    }

    //Login now becomes Principal
    //Testing login password encryption - commented on 9/7
//    public Principal login(LoginRequest request) {
//        User user = userDAO.getUserByUsernameAndPassword(request.getUsername(), request.getPassword());
//        if (user == null) throw new AuthenticationException("\nIncorrect username or password!!");
//        return new Principal(user.getUser_id(), user.getUsername(), user.getRole_id());
//    }

    public Principal login(LoginRequest request) {
        User user = userDAO.getUserByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (user == null) throw new AuthenticationException("\nIncorrect username or password!!");
        if (BCrypt.checkpw(user.getPassword(), request.getPassword())) {
            return new Principal(user.getUser_id(), user.getUsername(), user.getRole_id());
        } else {
            throw new InvalidRequestException("\n Incorrect password!");
        }

    }

    //  getUserByUsername method - Servlet
    public User getUserByUsername(String username) { //8/29
        if (userDAO.getUserByUsername(username) == null)
            throw new InvalidRequestException("\nInvalid request! There is no user by that username");
        return userDAO.getUserByUsername(username);
    }


    //Get all users 8/29
    public List<User> getAllUsers() {
        return userDAO.getAll(); // get all users from UserDAO
    }


    public boolean isValidUsername(String username) {
        if (!username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$"))
            throw new InvalidRequestException(RED + "\n Username must be is 8 more characters long." + RESET);
        return true;
    }

    public boolean isValidPassword(String password) {
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"))
            throw new InvalidRequestException(RED + "\n Password minimum  is eight characters long." + RESET);
        return true;
    }


    //Check for duplicate username based on username that is in the database
    public boolean isDuplicateUsername(String username) {
        if (userDAO.getUsername(username) != null)
            throw new ResourceConflictException(RED + "\nSorry, this " + username + " already has been taken!" + RESET);
        return false;
    }


    public boolean isSamePassword(String password, String password2) {
        if (!password.equals(password2)) throw new InvalidRequestException(RED + "\nPassword do not match!" + RESET);
        return true;
    }

    public boolean isValidEmail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
            throw new InvalidRequestException(RED + "\nInvalid email address. Please enter valid email." + RESET);
        return true;
    }


    //Check user full name - validation
    public boolean isValidName(String name) {
        if (name.isEmpty()) throw new InvalidRequestException(RED + "\nName is required!" + RESET);
        if (name.length() < 5)
            throw new InvalidRequestException(RED + "\nName must be more than 5 characters. " + RESET);
        return true;
    }


}