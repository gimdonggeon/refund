package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long toCurrencyId;
    private BigDecimal amountBeforeExchangeKrw;
    private BigDecimal amountAfterExchange;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_currency_id", insertable = false, updatable = false)
    private Currency toCurrency;

    public void cancel() {
        this.status = "cancelled";
    }

    public void calculateAmountAfterExchange(BigDecimal exchangeRate) {
        this.amountAfterExchange = getAmountBeforeExchangeKrw().divide(exchangeRate, 2, RoundingMode.HALF_UP);
    }
}
