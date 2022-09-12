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

public class ReimbursementTypeServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private final ReimbursementTypeService reimbursementTypeService;

    public ReimbursementTypeServlet(ObjectMapper mapper, ReimbursementTypeService reimbursementTypeService) {
        this.mapper = mapper;
        this.reimbursementTypeService = reimbursementTypeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);

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
        super.doDelete(req, resp);
    }
}
