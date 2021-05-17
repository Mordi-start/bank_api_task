package ru.dmitrymorel.bank_api_task.model;

import java.util.Objects;

public class Account {

    private int id;
    private String number;
    private double balance;
    private int userId;

    public Account() {
    }

    public Account(String number, double balance, int userId) {
        this.number = number;
        this.balance = balance;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return id == account.id && Double.compare(account.balance, balance) == 0 && userId == account.userId && Objects.equals(number, account.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, balance, userId);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                ", userId=" + userId +
                '}';
    }
}
