package com.revature.northern.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.northern.daos.UserDAO;
import com.revature.northern.daos.UserRoleDAO;

import com.revature.northern.services.TokenService;
import com.revature.northern.services.UserRoleService;
import com.revature.northern.services.UserService;
import com.revature.northern.servlets.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* ObjectMapper provides functionality for reading and writing JSON, either to and from basic POJOs (Plain Old Java Objects) */
        ObjectMapper mapper = new ObjectMapper();

        /* Dependency Injection */
        TestServlet testServlet = new TestServlet();
//        UserServlet userServlet = new UserServlet(mapper, new UserService(new UserDAO()));
        UserServlet userServlet = new UserServlet(mapper, new UserService(new UserDAO()));

        //All users
        UsersServlet usersServlet = new UsersServlet(mapper, new UserService(new UserDAO()));

//        ReimbursementServlet reimbursementServlet = new ReimbursementServlet(mapper, new ReimbursementService(new ReimbursementDOA()));
        UserRoleServlet userRoleServlet = new UserRoleServlet(mapper, new TokenService(new JwtConfig()), new UserRoleService(new UserRoleDAO()));




        /* Need ServletContext class to map whatever servlet to url path. */
        ServletContext context = sce.getServletContext();
        context.addServlet("TestServlet", testServlet).addMapping("/test");
        context.addServlet("UserServlet", userServlet).addMapping("/users/signup"); //users/signup
        context.addServlet("UserServlet", userServlet).addMapping("/users/auth"); // for signup
        context.addServlet("UsersServlet", usersServlet).addMapping("/users"); // for all users


//        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
//        context.addServlet("AuthServlet",usersServlet).addMapping("/auth");
//        context.addServlet("ReimbursementServlet",reimbursementServlet).addMapping("/reimbursment");

        context.addServlet("userRoleServlet", userRoleServlet).addMapping("role");


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\nShutting down northern web application");
    }


}
