package com.revature.northern.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.northern.dtos.requests.NewReimbursementRequest;
import com.revature.northern.models.Reimbursement;
import com.revature.northern.services.ReimbursementService;
import com.revature.northern.utils.custom_exceptions.InvalidSQLException;
import com.revature.northern.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReimbursementServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final ReimbursementService reimbursementService;

    public ReimbursementServlet(ObjectMapper mapper, ReimbursementService reimbursementService) {
        this.mapper = mapper;
        this.reimbursementService = reimbursementService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);

        try {

            NewReimbursementRequest request = mapper.readValue(req.getInputStream(), NewReimbursementRequest.class);
            Reimbursement createdReimbursement = reimbursementService.createReimbursement(request);

            resp.setContentType("application/json");
            resp.setStatus(200);
            resp.getWriter().write("<p>New reimbursement has been created successfully!!</p>");
            resp.getWriter().write(mapper.writeValueAsString(createdReimbursement.getReim_id()));
            resp.getWriter().write(mapper.writeValueAsString(createdReimbursement.getType_id()));


        } catch (InvalidSQLException e) {
            resp.getWriter().write("ReimbursementServlet: Unable to insert reimbursementType data!!");
            resp.setStatus(404);
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.getWriter().write("Reimbursement route doesn't exist!");
            resp.setStatus(409);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
