package ru.dmitrymorel.bank_api_task.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Card {

    private int id;
    private String number;
    private int accountId;
    private boolean enabled;

    public Card() {
    }

    public Card(String number, int accountId, boolean enabled) {
        this.number = number;
        this.accountId = accountId;
        this.enabled = enabled;
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return id == card.id && accountId == card.accountId && enabled == card.enabled && Objects.equals(number, card.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, accountId, enabled);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", accountId=" + accountId +
                ", enabled=" + enabled +
                '}';
    }
}
