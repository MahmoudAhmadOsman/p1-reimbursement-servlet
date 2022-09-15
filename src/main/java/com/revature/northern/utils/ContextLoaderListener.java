package com.revature.northern.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.northern.daos.*;
import com.revature.northern.services.*;
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

        ReimbursementTypeServlet reimbursementTypeServlet = new ReimbursementTypeServlet(mapper, new ReimbursementTypeService(new ReimbursementTypeDAO()));

        ReimbursementStatusServlet reimbursementStatusServlet = new ReimbursementStatusServlet(mapper, new ReimbursementStatusService(new ReimbursementStatusDAO()));

        ReimbursementServlet reimbursementServlet = new ReimbursementServlet(mapper, new ReimbursementService(new ReimbursementDOA()));

        UserRoleServlet userRoleServlet = new UserRoleServlet(mapper, new UserRoleService(new UserRoleDAO())); //UserRoles


        /* Need ServletContext class to map whatever servlet to url path. */
        ServletContext context = sce.getServletContext();
        context.addServlet("TestServlet", testServlet).addMapping("/test");
//         context.addServlet("UserServlet", userServlet).addMapping("/users/signup");
        //context.addServlet("UserServlet", userServlet).addMapping("/users/auth"); //  user login
        context.addServlet("UserServlet", userServlet).addMapping("/users/*"); // all users


        //UserRoles route
        context.addServlet("UserRoleServlet", userRoleServlet).addMapping("/users/roles");  //northern/roles

        context.addServlet("ReimbursementTypeServlet", reimbursementTypeServlet).addMapping("/types");

        //Reimbursement Status
        context.addServlet("ReimbursementStatusServlet", reimbursementStatusServlet).addMapping("/types/status");

        //Reimbursements rout
        context.addServlet("ReimbursementServlet", reimbursementServlet).addMapping("/reimbursements"); //northern/reimbursements


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\nShutting down northern web application!!!");
    }


}
