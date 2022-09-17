package com.revature.northern.daos;

import com.revature.northern.models.User;
import com.revature.northern.utils.custom_exceptions.InvalidSQLException;
import com.revature.northern.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User> {


    @Override
    public void save(User obj) {

        System.out.println("================" + obj + "================");

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (user_id, username, password, email, given_name, surname, is_active, role_id) values (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getUser_id());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getEmail());
            ps.setString(5, obj.getGiven_name());
            ps.setString(6, obj.getSurname());
            ps.setBoolean(7, false);
            ps.setString(8, "001");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database!!" + e.getMessage());
        }

    }


    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {

    }


    //Get user by its id number
    @Override
    public User getById(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

//            if (rs.next()) {
//                return new User(rs.getString("id"),
//                        rs.getString("username"),
//                        rs.getString("password"),
//                        rs.getString("name"),
//                        rs.getString("email"),
//                        rs.getString("role"));
//            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.", e);
        }
        return null;
    }


    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGiven_name(rs.getString("given_name"));
                user.setSurname(rs.getString("surname"));
                user.setIs_active(rs.getBoolean("is_active"));
                user.setRole_id(rs.getString("role_id"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to get all users from the database.");
        }

        return userList;
    }


    //Check for duplicate user based on username
    public String getUsername(String username) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            //if there is a data, then get it using .next() built method
            if (rs.next()) return rs.getString("username");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred while tyring to save user to the database!!" + e.getMessage());

        }

        return null; // if there is no data return null or nothing
    }


    //Check duplicate username
    public User getUserByUsernameAndPassword(String username, String password) {
//        User user = null;
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                rs.getString("user_id");
                rs.getString("username");
                rs.getString("email");
                rs.getString("given_name");
                rs.getString("surname");
                rs.getString("is_active");
                rs.getString("role_id");
//                return user;
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred while tyring to query user info from the database!!");
        }

        return null;

    }


    //Get by username
    public User getUserByUsername(String username) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                rs.getString("username");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred while tyring to query user from the database!!" + e.getMessage());
        }

        return null;
    }

}
