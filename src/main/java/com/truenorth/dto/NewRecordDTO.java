package com.truenorth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewRecordDTO {

    @JsonProperty
    private String userName;
    @JsonProperty
    private String operationType;
    @JsonProperty
    private Double amount = 0.0;

    public NewRecordDTO() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
