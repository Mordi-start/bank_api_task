package ru.dmitrymorel.bank_api_task.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Card {

    @JsonProperty("Id")
    private int id;
    @JsonProperty("Number")
    private String number;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("PaymentSystem")
    private String paymentSystem;
    @JsonProperty("AccountId")
    private int accountId;

    public Card() {
    }

    public Card(String number, String type, String paymentSystem, int accountId) {
        this.number = number;
        this.type = type;
        this.paymentSystem = paymentSystem;
        this.accountId = accountId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPaymentSystem() {
        return paymentSystem;
    }

    public void setPaymentSystem(String paymentSystem) {
        this.paymentSystem = paymentSystem;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return id == card.id && accountId == card.accountId && Objects.equals(number, card.number) && Objects.equals(type, card.type) && Objects.equals(paymentSystem, card.paymentSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, type, paymentSystem, accountId);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                ", paymentSystem='" + paymentSystem + '\'' +
                ", accountId=" + accountId +
                '}';
    }
}
