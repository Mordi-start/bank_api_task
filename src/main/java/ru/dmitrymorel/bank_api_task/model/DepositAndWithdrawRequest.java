package ru.dmitrymorel.bank_api_task.model;

import lombok.*;

import java.math.BigDecimal;

@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class DepositAndWithdrawRequest {
    private int cardId;
    private BigDecimal value;

    public DepositAndWithdrawRequest(int cardId, BigDecimal value) {
        this.cardId = cardId;
        this.value = value;
    }
}
