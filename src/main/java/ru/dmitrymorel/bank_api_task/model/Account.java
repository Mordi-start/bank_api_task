package ru.dmitrymorel.bank_api_task.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Account {
    private int id;
    private String number;
    private BigDecimal balance;
    private int userId;
    private boolean enabled;
}
