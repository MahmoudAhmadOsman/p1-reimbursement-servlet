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

public class UsersServlet extends HttpServlet {
    //1. inject ObjectMapper class & UserService class
    private final ObjectMapper mapper;
    private final UserService userService;

    public UsersServlet(ObjectMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;


    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = userService.getAllUsers();
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(userList));
    }


}
