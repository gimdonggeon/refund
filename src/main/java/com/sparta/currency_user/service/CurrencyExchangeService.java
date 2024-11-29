package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.CurrencyExchangeRequestDto;
import com.sparta.currency_user.dto.CurrencyExchangeResponseDto;
import com.sparta.currency_user.entity.CurrencyExchange;
import com.sparta.currency_user.repository.CurrencyExchangeRepository;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeService {

    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;

    @Transactional
    public CurrencyExchangeResponseDto save(CurrencyExchangeRequestDto currencyExchangeRequestDto) {
        userRepository.findById(currencyExchangeRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        var toCurrency = currencyRepository.findById(currencyExchangeRequestDto.getToCurrencyId())
                .orElseThrow(() -> new IllegalArgumentException("환전 대상 통화가 존재하지 않습니다."));

        CurrencyExchange currencyExchange = currencyExchangeRequestDto.toEntity();

        currencyExchange.calculateAmountAfterExchange(toCurrency.getExchangeRate());

        CurrencyExchange savedCurrencyExchange = currencyExchangeRepository.save(currencyExchange);

        return new CurrencyExchangeResponseDto(savedCurrencyExchange);
    }

    public List<CurrencyExchangeResponseDto> findByUserId(Long userId) {
        List<CurrencyExchange> exchanges = currencyExchangeRepository.findAllByUserId(userId);
        if (exchanges.isEmpty()) {
            throw new EntityNotFoundException("사용자와 관련된 환전 기록이 없습니다.");
        }
        return exchanges.stream()
                .map(CurrencyExchangeResponseDto::currencyExchangeResponseDto)
                .toList();
    }

    @Transactional
    public void cancel(Long id) {
        CurrencyExchange currencyExchange = currencyExchangeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("환전 요청이 존재하지 않습니다."));

        currencyExchange.cancel();

        userRepository.save(currencyExchange.getUser());
    }

    @Transactional
    public void deleteByUserId(Long userId) {
        List<CurrencyExchange> exchanges = currencyExchangeRepository.findAllByUserId(userId);
        if (exchanges.isEmpty()) {
            throw new EntityNotFoundException("사용자와 관련된 환전 기록이 없습니다.");
        }
        currencyExchangeRepository.deleteAll(exchanges);
    }
}