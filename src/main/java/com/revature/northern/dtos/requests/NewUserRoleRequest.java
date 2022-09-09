package com.revature.northern.dtos.requests;

public class NewUserRoleRequest {
    private String role_id;
    private String role;

    public NewUserRoleRequest() {
    }

    public NewUserRoleRequest(String role_id, String role) {
        this.role_id = role_id;
        this.role = role;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "NewUserRoleRequest{" +
                "role_id='" + role_id + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
