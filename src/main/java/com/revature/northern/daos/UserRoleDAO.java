package com.revature.northern.daos;

import com.revature.northern.models.UserRole;
import com.revature.northern.utils.custom_exceptions.InvalidSQLException;
import com.revature.northern.utils.database.ConnectionFactory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserRoleDAO implements CrudDAO<UserRole> {

    @Override
    public void save(UserRole obj)  {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("insert into ers_user_roles (role_id, role) values (?, ?)");
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

    }

    @Override
    public UserRole getById(String id) {
        return null;
    }

    @Override
    public List<UserRole> getAll() {
        return null;
    }
}
