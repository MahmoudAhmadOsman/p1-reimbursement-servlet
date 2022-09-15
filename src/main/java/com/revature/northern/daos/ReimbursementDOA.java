package com.revature.northern.daos;

import com.revature.northern.models.Reimbursement;
import com.revature.northern.utils.custom_exceptions.InvalidSQLException;
import com.revature.northern.utils.database.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDOA implements CrudDAO<Reimbursement>{
    @Override
    public void save(Reimbursement obj)  {
        System.out.println("===========" + obj + "===========");
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement
                    ps = con.prepareStatement("INSERT INTO ers_reimbursements (reim_id, amount, submitted, resolved, description, receipt, payment_id, author_id, resolver_id, status_id,type_id ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
            ps.setString(1, obj.getReim_id());
            ps.setDouble(2, obj.getAmount());
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setDate(4, Date.valueOf(LocalDate.now()));
            ps.setString(5, obj.getDescription());
            ps.setBytes(6, obj.getReceipt());
            ps.setString(7, obj.getPayment_id());
            ps.setString(8, obj.getAuthor_id());
            ps.setString(9, obj.getResolver_id());
            ps.setString(10, obj.getStatus_id());
            ps.setString(11, obj.getType_id());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save reimbursement into the database!! " + e.getMessage());
        }
    }

    @Override
    public void update(Reimbursement obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Reimbursement getById(String id) {
        return null;
    }

    @Override
    public List<Reimbursement> getAll() {
                List<Reimbursement> reimbursementList = new ArrayList<>();

                try (Connection con = ConnectionFactory.getInstance().getConnection()) {
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements");
                    ResultSet rs = ps.executeQuery();
//                    while (rs.next()) {
//                        Reimbursement ReimbList = new Reimbursement( rs.getString("reim_id"),
//                                rs.getDouble("amount"),
//                                rs.getDate("submitted").toLocalDate(),
//                                rs.getDate("resolved").toLocalDate(),
//                                rs.getString("description"),
//                                );
//                        reimbursementList.add(ReimbList);
//                    }

                } catch (SQLException e) {
                    throw new InvalidSQLException("An error occurred while tyring to get Reimbursement from the database.");
                }
               return reimbursementList;
    }
}
