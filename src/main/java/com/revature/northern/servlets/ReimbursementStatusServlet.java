package com.revature.northern.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.northern.dtos.requests.NewReimbursementStatus;
import com.revature.northern.models.ReimbursementStatus;
import com.revature.northern.services.ReimbursementStatusService;
import com.revature.northern.utils.custom_exceptions.InvalidSQLException;
import com.revature.northern.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReimbursementStatusServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private final ReimbursementStatusService reimbursementStatusService;

    public ReimbursementStatusServlet(ObjectMapper mapper, ReimbursementStatusService reimbursementStatusService) {
        this.mapper = mapper;
        this.reimbursementStatusService = reimbursementStatusService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // resp.getWriter().write("<p>ReimbursementStatus GET route!!");
        List<ReimbursementStatus> reimbursementStatusList = reimbursementStatusService.getAllReimbursementStatus();
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(reimbursementStatusList));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            NewReimbursementStatus request = mapper.readValue(req.getInputStream(), NewReimbursementStatus.class);
            ReimbursementStatus createdReimbursementStatus = reimbursementStatusService.createReimbursementStatus(request);
            resp.setContentType("application/json");
            resp.setStatus(200);
            resp.getWriter().write("<h1>New Reimbursement Status has been added successfully!! </h1>");
            resp.getWriter().write(mapper.writeValueAsString(createdReimbursementStatus.getStatus_id())); // return status id
            resp.getWriter().write(mapper.writeValueAsString(createdReimbursementStatus.getStatus())); // return status


        } catch (InvalidSQLException e) {
            resp.getWriter().write("Unable to insert reimbursement status data!!");
            resp.setStatus(404);
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.getWriter().write("This reimbursement status doesn't exist!");
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
