package com.revature.northern.services;

import com.revature.northern.daos.ReimbursementTypeDAO;
import com.revature.northern.dtos.requests.NewReimbursementRequest;
import com.revature.northern.dtos.requests.NewRequestReimbursementType;
import com.revature.northern.models.ReimbursementType;

import java.util.List;
import java.util.UUID;

public class ReimbursementTypeService {
    private final ReimbursementTypeDAO reimbursementTypeDAO;

    public ReimbursementTypeService(ReimbursementTypeDAO reimbursementTypeDAO) {
        this.reimbursementTypeDAO = reimbursementTypeDAO;
    }


    //Save ReimbursementType
    public ReimbursementType createReimbursementType(NewRequestReimbursementType request) {
        //ReimbursementType reimbursementType = new ReimbursementType(UUID.randomUUID().toString(), request.getType());
        ReimbursementType reimbursementType = new ReimbursementType(request.getType_id(), request.getType());
        reimbursementTypeDAO.save(reimbursementType);
        return reimbursementType;
    }



//Get All Reimbursement Types

    public List<ReimbursementType> getAllReimbursementType(){
        return  reimbursementTypeDAO.getAll();
    }


}
