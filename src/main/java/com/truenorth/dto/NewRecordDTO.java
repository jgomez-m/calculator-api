package com.truenorth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewRecordDTO {

    @JsonProperty
    private Long userId;
    @JsonProperty
    private Long operationId;
    @JsonProperty
    private Double amount = 0.0;

    public NewRecordDTO() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
