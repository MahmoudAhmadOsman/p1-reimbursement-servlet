package com.revature.northern.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.northern.dtos.requests.NewUserRequest;
import com.revature.northern.dtos.responses.Principal;
import com.revature.northern.models.User;
import com.revature.northern.services.TokenService;
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
    private final TokenService tokenService;
    private final UserService userService;

    public UserServlet(ObjectMapper mapper, TokenService tokenService, UserService userService) {
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.userService = userService;
    }


    //Every user can register
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("User POST method!!");
        try {
            NewUserRequest request = mapper.readValue(req.getInputStream(), NewUserRequest.class);
            User createdUser = userService.register(request);
            resp.setContentType("application/json");
            resp.setStatus(200);
            resp.getWriter().write(mapper.writeValueAsString(createdUser.getUser_id()));

        } catch (InvalidRequestException e) {
            resp.setStatus(404); //BAD REQUEST
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
            resp.getWriter().write(mapper.writeValueAsString(e.getMessage()));
        } catch (ResourceConflictException e) {
            resp.setStatus(409);
        }

    }


    //Only ADMIN & PRINCIPAL can view the list
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = req.getHeader("Authorization");
        Principal principal = tokenService.extractRequesterDetails(token);

        try {
            if (principal.getRole().equals("ADMIN") || principal.getRole().equals("PRINCIPAL")) {

                String username = req.getParameter("username");
                resp.setContentType("application/json");

                if (username != null) {
                    resp.getWriter().write(mapper.writeValueAsString(userService.getUserByUsername(username)));

                } else {
                    List<User> userLsit = userService.getAllUsers();
                    resp.getWriter().write(mapper.writeValueAsString(userLsit));
                }
            } else {
                resp.setStatus(403); // FORBIDDEN REQUEST
            }

        } catch (NullPointerException e) {
            resp.setStatus(401); // UNAUTHORIZED REQUEST
        } catch (InvalidRequestException e) {
            resp.setStatus(404);
        }


    }


    // PRINCIPAL can view & update
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }



    //Only ADMIN can delete
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
