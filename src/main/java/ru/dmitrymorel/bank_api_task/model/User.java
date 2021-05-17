package ru.dmitrymorel.bank_api_task.model;

import java.util.List;
import java.util.Objects;

public class User {

    private int id;
    private String name;
    private String surname;
    private List<Account> accounts;
    private List<Card> cards;

    public User() {
    }

    public User(String name, String surname
            , List<Account> accounts, List<Card> cards) {
        this.name = name;
        this.surname = surname;
        this.accounts = accounts;
        this.cards = cards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(accounts, user.accounts) && Objects.equals(cards, user.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, accounts, cards);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", accounts=" + accounts +
                ", cards=" + cards +
                '}';
    }
}
