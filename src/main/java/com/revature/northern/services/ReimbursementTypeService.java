package com.revature.northern.services;

import com.revature.northern.daos.ReimbursementTypeDAO;
import com.revature.northern.dtos.requests.NewRequestReimbursementType;
import com.revature.northern.models.ReimbursementType;

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


}
