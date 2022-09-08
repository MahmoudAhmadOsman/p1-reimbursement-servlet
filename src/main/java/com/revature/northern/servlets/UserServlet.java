package com.revature.northern.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.northern.dtos.requests.NewUserRequest;

import com.revature.northern.models.User;
import com.revature.northern.services.UserService;
import com.revature.northern.utils.custom_exceptions.InvalidRequestException;
import com.revature.northern.utils.custom_exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    //1. inject ObjectMapper class & UserService class
    private final ObjectMapper mapper;
    private final UserService userService;

    public UserServlet(ObjectMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("inside doPost");
        try {
            NewUserRequest request = mapper.readValue(req.getInputStream(), NewUserRequest.class);
            User createdUser = userService.register(request);
            resp.setContentType("application/json");
            resp.setStatus(200);
            resp.getWriter().write(mapper.writeValueAsString(createdUser.getUser_id()));

        } catch (InvalidRequestException e) {
            resp.setStatus(404); //BAD REQUEST
//            resp.getWriter().write("Unable to insert data!!");
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.setStatus(409);
        }

    }


    //Get list of all users
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().write("<h1>UserServlet doGet method! </h1>");
        List<User> userLsit = userService.getAllUsers();
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(userLsit));
    }


}
