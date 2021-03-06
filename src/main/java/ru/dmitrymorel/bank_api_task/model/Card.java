package ru.dmitrymorel.bank_api_task.model;

import lombok.*;

import java.util.List;

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
//    private List<Transaction> transactions;

}
