package ru.dmitrymorel.bank_api_task.model;

import java.math.BigDecimal;

public class BalanceRequest {
    private int id;
    private BigDecimal balance;

    public BalanceRequest(int id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BalanceRequest{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
