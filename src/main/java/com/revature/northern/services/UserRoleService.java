package com.revature.northern.services;


import com.revature.northern.daos.UserRoleDAO;
import com.revature.northern.dtos.requests.NewUserRoleRequest;
import com.revature.northern.models.UserRole;

import java.io.IOException;
import java.util.UUID;

public class UserRoleService {
    private  final UserRoleDAO userRoleDAO;

    public UserRoleService(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }


    //Save UserRole service
    public UserRole saveUserRole(NewUserRoleRequest request) throws IOException {
        UserRole userRole = new UserRole(UUID.randomUUID().toString(), request.getRole());
        userRoleDAO.save(userRole);
        return userRole;
    }





}
