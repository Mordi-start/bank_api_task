package ru.dmitrymorel.bank_api_task.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    private int id;
    private String name;
    private String surname;
    private boolean enabled;
    private List<Account> accounts;
    private List<Card> cards;
    private List<User> users;
}
