package com.revature.northern.daos;

import com.revature.northern.models.ReimbursementType;
import com.revature.northern.utils.custom_exceptions.InvalidSQLException;
import com.revature.northern.utils.database.ConnectionFactory;

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
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("UPDATE ers_reimbursement_types SET type = ? WHERE type_id = ? ");
            ps.setString(1, obj.getType());
            ps.setString(2, obj.getType_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred while trying to update Reimbursement Type!" + e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement id_exist = con.prepareStatement("SELECT * FROM ers_reimbursement_types WHERE type_id = ?");
            id_exist.setString(1, id);
            ResultSet exists = id_exist.executeQuery();
            if (exists.next()) {
                PreparedStatement ps = con.prepareStatement("DELETE FROM ers_reimbursement_types WHERE type_id = ?");
                ps.setString(1, id);
                ps.executeUpdate();
                System.out.println("Reimbursement Type has been deleted successfully!");
            } else {
                System.out.println("No Reimbursement Type id found!. Please try again.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("\nAn error occurred while tyring to delete Reimbursement Type data from the database!!." + e.getMessage());
        }

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
