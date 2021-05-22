package ru.dmitrymorel.bank_api_task.model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Card {

    private int id;
    private String number;
    private int accountId;
    private boolean enabled;

    public Card(String number, int accountId, boolean enabled) {
        this.number = number;
        this.accountId = accountId;
        this.enabled = enabled;
    }
}
