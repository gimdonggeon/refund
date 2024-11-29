package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.CurrencyExchange;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CurrencyExchangeRequestDto {
    private Long userId;
    private Long toCurrencyId;
    private BigDecimal amountBeforeExchangeKrw;

    public CurrencyExchange toEntity() {
        return new CurrencyExchange(
                null, userId, toCurrencyId, amountBeforeExchangeKrw, null, "normal", null, null
        );
    }
}