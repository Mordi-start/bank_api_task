package ru.dmitrymorel.bank_api_task.model;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BalanceRequest {
    private int id;
    private BigDecimal balance;
}
