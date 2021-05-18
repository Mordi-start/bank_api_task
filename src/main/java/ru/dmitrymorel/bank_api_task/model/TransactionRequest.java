package ru.dmitrymorel.bank_api_task.model;

import java.math.BigDecimal;

public class TransactionRequest {

    private int gettingAccountId;
    private int sendAccountId;
    private BigDecimal income;

    public TransactionRequest(int gettingAccountId, int sendAccountId, BigDecimal income) {
        this.gettingAccountId = gettingAccountId;
        this.sendAccountId = sendAccountId;
        this.income = income;
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

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "gettingAccountId=" + gettingAccountId +
                ", sendAccountId=" + sendAccountId +
                ", income=" + income +
                '}';
    }
}
