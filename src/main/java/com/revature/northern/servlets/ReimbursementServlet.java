package com.revature.northern.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.northern.services.ReimbursementService;

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
        super.doPost(req, resp);
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
