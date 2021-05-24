package ru.dmitrymorel.bank_api_task.model;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Transaction {
    private int id;
    private String cardNumber;
    private String type;
    private BigDecimal sum;
    private boolean enabled;
}
