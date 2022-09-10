package com.revature.northern.daos;

import com.revature.northern.models.UserRole;
import com.revature.northern.utils.custom_exceptions.InvalidSQLException;
import com.revature.northern.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDAO implements CrudDAO<UserRole> {
    @Override
    public void save(UserRole obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO ers_user_roles (role_id, role) values (?, ?)");
            ps.setString(1, obj.getRole_id());
            ps.setString(2, obj.getRole());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("\nAn error occurred while tyring to save UserRole into the database!!.");
        }
    }

    @Override
    public void update(UserRole obj) {

    }

    @Override
    public void delete(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM ers_user_roles where role_id = ?");
            ps.setString(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred while trying to delete UserRole!! " + e.getMessage());
        }

    }

    @Override
    public UserRole getById(String id) {

        UserRole getByItsId = new UserRole();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            // check if id exit in the database
            PreparedStatement id_exist = con.prepareStatement("SELECT * FROM ers_user_roles WHERE role_id = ?");
            id_exist.setString(1, id);
            ResultSet rs = id_exist.executeQuery();

            if (rs.next()) {
                System.out.println("UserRole with role_id: " +
                        rs.getString("id") +
                        "[" + rs.getString("role_id") + "]" +
                        " doesn't exist!"
                );

            } else System.out.println("UserRole with this role_id is not found!!!"); // if role_id isn't found

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred while tyring find a UserRole!!");
        }

        return getByItsId;
    }

    @Override
    public List<UserRole> getAll() {
        List<UserRole> userRoleList = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_user_roles");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserRole userRole = new UserRole(
                        rs.getString("role_id"),
                        rs.getString("role")
                );
                userRoleList.add(userRole);
            }

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred while tyring to get userRoles from the database.");
        }
        return userRoleList;
    }
}
