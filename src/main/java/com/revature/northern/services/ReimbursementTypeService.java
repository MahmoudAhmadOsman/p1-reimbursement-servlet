package com.revature.northern.services;

import com.revature.northern.daos.ReimbursementTypeDAO;
import com.revature.northern.dtos.requests.NewRequestReimbursementType;
import com.revature.northern.models.ReimbursementType;
import com.revature.northern.utils.custom_exceptions.InvalidUserException;

import java.util.List;


public class ReimbursementTypeService {
    private final ReimbursementTypeDAO reimbursementTypeDAO;

    public ReimbursementTypeService(ReimbursementTypeDAO reimbursementTypeDAO) {
        this.reimbursementTypeDAO = reimbursementTypeDAO;
    }

    //Save ReimbursementType
    public ReimbursementType createReimbursementType(NewRequestReimbursementType request) {
        ReimbursementType reimbursementType = new ReimbursementType(request.getType_id(), request.getType());
        reimbursementTypeDAO.save(reimbursementType);
        return reimbursementType;
    }
    //Get All Reimbursement Types
    public List<ReimbursementType> getAllReimbursementType() {
        return reimbursementTypeDAO.getAll();
    }

    //Delete Reimbursement Type
    public void deleteReimbursementType(String id) {
        reimbursementTypeDAO.delete(id);
    }

    //update Reimbursement Type
    public void updateReimbursementType(ReimbursementType id) {
        reimbursementTypeDAO.update(id);
    }



    /******* ReimbursementType Validation methods *********/
    public boolean isValidType(String type) {
        if (type.isEmpty()) throw new InvalidUserException("\nType is required!!");
        if (type.length() < 4) throw new InvalidUserException("\nType must be more than 4 characters!!" );
        return true;
    }


}
