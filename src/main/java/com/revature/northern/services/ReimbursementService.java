package com.revature.northern.services;

import com.revature.northern.daos.ReimbursementDOA;
import com.revature.northern.dtos.requests.NewReimbursementRequest;
import com.revature.northern.models.Reimbursement;
import java.util.UUID;

public class ReimbursementService {
    private final ReimbursementDOA reimbursementDOA;

    public ReimbursementService(ReimbursementDOA reimbursementDOA) {
        this.reimbursementDOA = reimbursementDOA;
    }


    //Create new Reimbursement
    public Reimbursement createReimbursement(NewReimbursementRequest request ) {
        Reimbursement reimbursement = new Reimbursement();
            reimbursement.setReim_id(UUID.randomUUID().toString());
           reimbursement.setAmount(request.getAmount());
//           reimbursement.setSubmitted(request.getSubmitted());
//           reimbursement.setResolved(request.getResolved());
           reimbursement.setDescription(request.getDescription());
           reimbursement.setReceipt(request.getReceipt());
           reimbursement.setPayment_id(request.getPayment_id());
           reimbursement.setAuthor_id(request.getAuthor_id());
           reimbursement.setResolver_id(request.getResolver_id());
           reimbursement.setStatus_id(request.getStatus_id());
           reimbursement.setType_id(request.getType_id());

        reimbursementDOA.save(reimbursement);
            return reimbursement;

    }




}
