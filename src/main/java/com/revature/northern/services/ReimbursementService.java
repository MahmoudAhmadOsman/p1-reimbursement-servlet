package com.revature.northern.services;

import com.revature.northern.daos.ReimbursementDOA;
import com.revature.northern.dtos.requests.NewUserRoleRequest;
import com.revature.northern.models.Reimbursement;
import com.revature.northern.models.ReimbursementType;

import java.util.UUID;

public class ReimbursementService {
    private final ReimbursementDOA reimbursementDOA;

    public ReimbursementService(ReimbursementDOA reimbursementDOA) {
        this.reimbursementDOA = reimbursementDOA;
    }


    public Reimbursement createReimbursement(ReimbursementType request ) {
        Reimbursement reimbursement = new Reimbursement();

            reimbursement.setReim_id(UUID.randomUUID().toString());
//            reimbursement.setAmount(request.getAmount());

        reimbursementDOA.save(reimbursement);
            return reimbursement;
    }




}
