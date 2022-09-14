package com.revature.northern.daos;

import com.revature.northern.models.ReimbursementStatus;
import com.revature.northern.utils.custom_exceptions.InvalidSQLException;
import com.revature.northern.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementStatusDAO implements CrudDAO<ReimbursementStatus> {
    @Override
    public void save(ReimbursementStatus obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO ers_reimbursement_statuses (status_id, status) values (?, ?)");
            ps.setString(1, obj.getStatus_id());
            ps.setString(2, obj.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("\nAn error occurred while tyring to save Reimbursement Status data into the database!!." + e.getMessage());
        }

    }

    @Override
    public void update(ReimbursementStatus obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public ReimbursementStatus getById(String id) {
        return null;
    }

    @Override
    public List<ReimbursementStatus> getAll() {
        List<ReimbursementStatus> reimbursementStatusList = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursement_statuses");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReimbursementStatus ReimbStatusType = new ReimbursementStatus(
                        rs.getString("status_id"),
                        rs.getString("status"));
                reimbursementStatusList.add(ReimbStatusType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred while tyring to get Reimbursement Status from the database." + e.getMessage());
        }


        return reimbursementStatusList;
    }
}
