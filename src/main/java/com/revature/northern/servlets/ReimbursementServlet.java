package com.revature.northern.servlets;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.northern.services.ReimbursementService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReimbursementServlet extends HttpServlet {
//    private final ObjectMapper mapper;
//    private final ReimbursementService reimbursementService;
//
//    public ReimbursementServlet(ObjectMapper mapper, ReimbursementService reimbursementService) {
//        this.mapper = mapper;
//        this.reimbursementService = reimbursementService;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        try {
////
////            NewReimbursementRequest request = mapper.readValue(req.getInputStream(), NewReimbursementRequest.class);
////
////            Reimbursement createdReimbursement = reimbursementService.saveReimbursement(request);
////            resp.setContentType("application/json");
////            resp.setStatus(200);
////            resp.getWriter().write(mapper.writeValueAsString(createdReimbursement.getReim_id()));
////
////        } catch (InvalidRequestException e) {
////            resp.setStatus(404); //BAD REQUEST
//////            resp.getWriter().write("Unable to insert data!!");
////            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
////            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
////        } catch (ResourceConflictException e) {
////            resp.setStatus(409);
////        }
//    }
}
