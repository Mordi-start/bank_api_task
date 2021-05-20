package ru.dmitrymorel.bank_api_task.model;

import java.math.BigDecimal;

public class TransactionRequest {

    private int gettingAccountId;
    private int sendAccountId;
    private BigDecimal value;

    public TransactionRequest() {
    }

    public TransactionRequest(int sendAccountId, int gettingAccountId, BigDecimal value) {
        this.gettingAccountId = gettingAccountId;
        this.sendAccountId = sendAccountId;
        this.value = value;
    }

    public int getGettingAccountId() {
        return gettingAccountId;
    }

    public void setGettingAccountId(int gettingAccountId) {
        this.gettingAccountId = gettingAccountId;
    }

    public int getSendAccountId() {
        return sendAccountId;
    }

    public void setSendAccountId(int sendAccountId) {
        this.sendAccountId = sendAccountId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "sendAccountId=" + sendAccountId +
                ", gettingAccountId=" + gettingAccountId +
                ", value=" + value +
                '}';
    }
}
