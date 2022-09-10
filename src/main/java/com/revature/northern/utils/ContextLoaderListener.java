package com.revature.northern.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.northern.daos.ReimbursementDOA;
import com.revature.northern.daos.UserDAO;
import com.revature.northern.daos.UserRoleDAO;
import com.revature.northern.services.ReimbursementService;
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
        UserServlet userServlet = new UserServlet(mapper, new UserService(new UserDAO()));
        ReimbursementServlet reimbursementServlet = new ReimbursementServlet(mapper, new ReimbursementService(new ReimbursementDOA()));
        UserRoleServlet userRoleServlet = new UserRoleServlet(mapper, new UserRoleService(new UserRoleDAO())); //UserRoles



        /* Need ServletContext class to map whatever servlet to url path. */
        ServletContext context = sce.getServletContext();
        context.addServlet("TestServlet", testServlet).addMapping("/test");
//         context.addServlet("UserServlet", userServlet).addMapping("/users/signup");
        //context.addServlet("UserServlet", userServlet).addMapping("/users/auth"); //  user login
        context.addServlet("UserServlet", userServlet).addMapping("/users/*"); // all users


        //UserRoles route
        context.addServlet("UserRoleServlet", userRoleServlet).addMapping("/roles");  // http://localhost:8080/northern/roles

        //Reimbursements rout
        context.addServlet("ReimbursementServlet", reimbursementServlet).addMapping("/reimbursements");


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\nShutting down northern web application!!!");
    }


}
