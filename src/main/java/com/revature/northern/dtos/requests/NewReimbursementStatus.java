package com.revature.northern.dtos.requests;

public class NewReimbursementStatus {
    private String status_id;
    private String status;

    public NewReimbursementStatus() {
    }

    public NewReimbursementStatus(String status_id, String status) {
        this.status_id = status_id;
        this.status = status;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NewReimbursementStatus{" +
                "status_id='" + status_id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
