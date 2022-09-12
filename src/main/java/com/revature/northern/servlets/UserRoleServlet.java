package com.revature.northern.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.northern.dtos.requests.NewUserRoleRequest;
import com.revature.northern.models.UserRole;
import com.revature.northern.services.TokenService;
import com.revature.northern.services.UserRoleService;
import com.revature.northern.utils.custom_exceptions.InvalidSQLException;
import com.revature.northern.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserRoleServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final UserRoleService userRoleService;


    public UserRoleServlet(ObjectMapper mapper, UserRoleService userRoleService) {
        this.mapper = mapper;
        this.userRoleService = userRoleService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        // resp.getWriter().write("<h1>UserRoles doGet method!! </h1>");
        List<UserRole> userRoleList = userRoleService.getAllUsrRoles();
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(userRoleList));


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doPost(req, resp);
        //resp.getWriter().write("<h1>UserRoles doPost method!! </h1>");

        try {
            NewUserRoleRequest request = mapper.readValue(req.getInputStream(), NewUserRoleRequest.class);
            UserRole createUserRole = userRoleService.saveUserRole(request);
            resp.setContentType("application/json");
            resp.setStatus(200);
            resp.getWriter().write("<p>New UserRole has been created successfully!! </p>");
            resp.getWriter().write(mapper.writeValueAsString(createUserRole.getRole_id())); // return back the UserRole id
            resp.getWriter().write(mapper.writeValueAsString(createUserRole.getRole())); // return back UserRole role


        } catch (InvalidSQLException e) {
            resp.getWriter().write("Unable to insert UserRole data!!");
            resp.setStatus(404);
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.getWriter().write("UserRoute route doesn't exist!");
            resp.setStatus(409);
        }


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            NewUserRoleRequest request = mapper.readValue(req.getInputStream(), NewUserRoleRequest.class);
            userRoleService.deleteUserRole(request.getRole_id());
            resp.setContentType("application/json");
            resp.setStatus(200);
            resp.getWriter().write("<p>UserRole deleted successfully!!</p>");


        } catch (InvalidSQLException e) {
            resp.getWriter().write("Unable to delete UserRole!!");
            resp.setStatus(404);
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.getWriter().write("This UserRoute doesn't exist!");
            resp.setStatus(409);
        }

    }
}
