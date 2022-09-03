package com.revature.northern.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.northern.dtos.requests.NewUserRoleRequest;
import com.revature.northern.models.UserRole;
import com.revature.northern.services.TokenService;
import com.revature.northern.services.UserRoleService;
import com.revature.northern.utils.custom_exceptions.InvalidRequestException;
import com.revature.northern.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRoleServlet extends HttpServlet {
    //1st inject ObjectMapper, TokenService  & UserRoleService
    private final ObjectMapper mapper;
    private TokenService tokenService;
    private final UserRoleService userRoleService;

    public UserRoleServlet(ObjectMapper mapper, TokenService tokenService, UserRoleService userRoleService) {
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.userRoleService = userRoleService;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        try {
            NewUserRoleRequest request = mapper.readValue(req.getInputStream(), NewUserRoleRequest.class);
            UserRole createdUserRole = userRoleService.saveUserRole(request);
            resp.setContentType("application/json");
            resp.setStatus(200);
            resp.getWriter().write(mapper.writeValueAsString(createdUserRole.getRole_id()));


        } catch (InvalidRequestException e) {
            resp.setStatus(404);
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.setStatus(409);
        } catch (Exception e) {
            resp.setStatus(404);
        }

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
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
