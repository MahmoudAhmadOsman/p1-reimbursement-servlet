package com.revature.northern.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.northern.dtos.requests.NewRequestReimbursementType;
import com.revature.northern.models.ReimbursementType;
import com.revature.northern.services.ReimbursementTypeService;
import com.revature.northern.utils.custom_exceptions.InvalidSQLException;
import com.revature.northern.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReimbursementTypeServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private final ReimbursementTypeService reimbursementTypeService;

    public ReimbursementTypeServlet(ObjectMapper mapper, ReimbursementTypeService reimbursementTypeService) {
        this.mapper = mapper;
        this.reimbursementTypeService = reimbursementTypeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().write("<h1>Get reimbursement type route!! </h1>");
        List<ReimbursementType> reimbursementTypeList = reimbursementTypeService.getAllReimbursementType();
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(reimbursementTypeList));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            NewRequestReimbursementType request = mapper.readValue(req.getInputStream(), NewRequestReimbursementType.class);
            ReimbursementType createReimbursementType = reimbursementTypeService.createReimbursementType(request);
            resp.setContentType("application/json");
            resp.setStatus(200);
            resp.getWriter().write("<h1>New reimbursement type has been added successfully!! </h1>");
            resp.getWriter().write(mapper.writeValueAsString(createReimbursementType.getType_id())); // return type
            resp.getWriter().write(mapper.writeValueAsString(createReimbursementType.getType_id())); // return type id


        } catch (InvalidSQLException e) {
            resp.getWriter().write("Unable to insert reimbursement types data!!");
            resp.setStatus(404);
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.getWriter().write("This reimbursement types doesn't exist!");
            resp.setStatus(409);
        }


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doDelete(req, resp);
        try {
            NewRequestReimbursementType request = mapper.readValue(req.getInputStream(), NewRequestReimbursementType.class);
            if (request.getType_id().isEmpty() || request.getType_id() != request.getType_id()) {
                resp.getWriter().write("<h1>Reimbursement type doesn't exist!! </h1>");
            } else {
                reimbursementTypeService.deleteReimbursementType(request.getType_id());
                resp.setContentType("application/json");
                resp.setStatus(200);
                resp.getWriter().write("<h1>Reimbursement type has been DELETED successfully!! </h1>");
            }

        } catch (InvalidSQLException e) {
            resp.getWriter().write("Unable to delete reimbursement types data!!");
            resp.setStatus(404);
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.getWriter().write("This reimbursement types doesn't exist!");
            resp.setStatus(409);
        }

    }
}
