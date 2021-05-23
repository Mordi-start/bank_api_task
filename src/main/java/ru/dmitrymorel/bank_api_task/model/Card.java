package ru.dmitrymorel.bank_api_task.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Card {
    private int id;
    private String number;
    private int accountId;
    private boolean enabled;
}
