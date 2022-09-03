package com.revature.northern.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.northern.dtos.requests.LoginRequest;
import com.revature.northern.dtos.responses.Principal;
import com.revature.northern.services.TokenService;
import com.revature.northern.services.UserService;
import com.revature.northern.utils.custom_exceptions.AuthenticationException;
import com.revature.northern.utils.custom_exceptions.InvalidRequestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthServlet extends HttpServlet {

    //1st, inject
    private final ObjectMapper mapper;
    private final TokenService tokenService;
    private final UserService userService;

    public AuthServlet(ObjectMapper mapper, TokenService tokenService, UserService userService) {
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LoginRequest request = mapper.readValue(req.getInputStream(), LoginRequest.class);
            Principal principal = userService.login(request); //allow user login

            String token = tokenService.generateToken(principal);

            resp.setStatus(200);
            resp.setHeader("Authorization", token);
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(principal));
        } catch (InvalidRequestException e) {
            resp.setStatus(404); // BAD REQUEST
        } catch (AuthenticationException e) {
            resp.setStatus(401); // INVALID CREDENTIALS
        }
    }
}
