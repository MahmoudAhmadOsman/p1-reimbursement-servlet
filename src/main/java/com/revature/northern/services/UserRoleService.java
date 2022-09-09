package com.revature.northern.services;

import com.revature.northern.daos.UserRoleDAO;
import com.revature.northern.dtos.requests.NewUserRoleRequest;
import com.revature.northern.models.UserRole;
import com.revature.northern.utils.custom_exceptions.InvalidRequestException;
import com.revature.northern.utils.custom_exceptions.InvalidUserException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class UserRoleService {
    private final UserRoleDAO userRoleDAO;

    public UserRoleService(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }


    //Save UserRoleService method
    public UserRole saveUserRole(NewUserRoleRequest request) {
        UserRole userRole = new UserRole(UUID.randomUUID().toString(), request.getRole());
        userRoleDAO.save(userRole);
        return userRole;
    }


    //get all UserRoles
    public List<UserRole> getAllUsrRoles() {
        return userRoleDAO.getAll();
    }


    //Delete UserRole method
    public void deleteUserRole(String id) {
        userRoleDAO.delete(id);
    }


    //Get UserRole by id
    public UserRole getUserRoleById(String id) {
        return userRoleDAO.getById(id);
    }

    public void updateUserRole(UserRole id) {
        userRoleDAO.update(id);
    }

    // public UserRole updateUserRole(UserRole role_id) {
    //        userRoleDAO.update(role_id);
    //    }


    /******* UserRoleService Validations *********/

    public boolean isValidRoleId(String role_id) {
        if (role_id.isEmpty()) throw new InvalidUserException("\nUserRole id is required!!");
        if (role_id.length() < 2)
            throw new InvalidUserException("\nRole id must be 2 or more characters!");
        return true;
    }

    public boolean isValidRole(String role) {
        if (role.isEmpty()) throw new InvalidUserException("\nUser role  is required!!");
        if (role.length() < 3)
            throw new InvalidUserException("\nUser role  must be 3 or more characters!");
        return true;
    }


}
