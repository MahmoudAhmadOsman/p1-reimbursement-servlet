package com.revature.northern.services;


import com.revature.northern.daos.ReimbursementStatusDAO;
import com.revature.northern.dtos.requests.NewReimbursementStatus;
import com.revature.northern.models.ReimbursementStatus;

import java.util.List;


public class ReimbursementStatusService {
    private final ReimbursementStatusDAO reimbursementStatusDAO;

    public ReimbursementStatusService(ReimbursementStatusDAO reimbursementStatusDAO) {
        this.reimbursementStatusDAO = reimbursementStatusDAO;
    }


    //create new ReimbursementStatus
    public ReimbursementStatus createReimbursementStatus(NewReimbursementStatus request) {
        ReimbursementStatus reimbursementStatus = new ReimbursementStatus(request.getStatus_id(), request.getStatus());
        reimbursementStatusDAO.save(reimbursementStatus);
        return reimbursementStatus;

    }


    //  //Get All Reimbursement Status
    public List<ReimbursementStatus> getAllReimbursementStatus() {
        return reimbursementStatusDAO.getAll();
    }

}
