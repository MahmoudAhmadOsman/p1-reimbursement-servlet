package com.revature.northern.dtos.requests;
import java.time.LocalDate;

public class NewReimbursementRequest {
    private double amount;
    private String description;
    private LocalDate submitted;
    private String payment_id;
    private String type_id;

    public NewReimbursementRequest() {
    }

    public NewReimbursementRequest(double amount, String description, LocalDate submitted, String payment_id, String type_id) {
        this.amount = amount;
        this.description = description;
        this.submitted = submitted;
        this.payment_id = payment_id;
        this.type_id = type_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getSubmitted() {
        return submitted;
    }

    public void setSubmitted(LocalDate submitted) {
        this.submitted = submitted;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "NewReimbursementRequest{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", submitted=" + submitted +
                ", payment_id='" + payment_id + '\'' +
                ", type_id='" + type_id + '\'' +
                '}';
    }
}
