package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.CurrencyExchange;

import java.math.BigDecimal;

public class CurrencyExchangeResponseDto {
    private Long id;
    private Long userId;
    private Long toCurrencyId;
    private BigDecimal amountBeforeExchangeKrw;
    private BigDecimal amountAfterExchange;
    private String status;

    public CurrencyExchangeResponseDto(CurrencyExchange currencyExchange) {
        this.id = currencyExchange.getId();
        this.userId = currencyExchange.getUserId();
        this.toCurrencyId = currencyExchange.getToCurrencyId();
        this.amountBeforeExchangeKrw = currencyExchange.getAmountBeforeExchangeKrw();
        this.amountAfterExchange = currencyExchange.getAmountAfterExchange();
        this.status = currencyExchange.getStatus();
    }

    public static CurrencyExchangeResponseDto currencyExchangeResponseDto(CurrencyExchange currencyExchange) {
        return new CurrencyExchangeResponseDto(currencyExchange);
    }
}
