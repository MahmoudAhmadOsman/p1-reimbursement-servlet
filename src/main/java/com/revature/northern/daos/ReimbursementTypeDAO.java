package com.revature.northern.daos;

import com.revature.northern.models.ReimbursementType;
import com.revature.northern.models.UserRole;
import com.revature.northern.utils.custom_exceptions.InvalidSQLException;
import com.revature.northern.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementTypeDAO implements CrudDAO<ReimbursementType> {
    @Override
    public void save(ReimbursementType obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO ers_reimbursement_types (type_id, type) values (?, ?)");
            ps.setString(1, obj.getType_id());
            ps.setString(2, obj.getType());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("\nAn error occurred while tyring to save Reimbursement Type data into the database!!." + e.getMessage());
        }
    }

    @Override
    public void update(ReimbursementType obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public ReimbursementType getById(String id) {
        return null;
    }

    @Override
    public List<ReimbursementType> getAll() {
        List<ReimbursementType> ReimbTypeleList = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursement_types");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReimbursementType ReimbType = new ReimbursementType(rs.getString("type_id"), rs.getString("type"));
                ReimbTypeleList.add(ReimbType);
            }

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred while tyring to get ReimbursementType from the database.");
        }
        return ReimbTypeleList;
    }
}
