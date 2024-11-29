package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.CurrencyExchangeRequestDto;
import com.sparta.currency_user.dto.CurrencyExchangeResponseDto;
import com.sparta.currency_user.service.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final CurrencyExchangeService currencyExchangeService;

    @PostMapping
    public ResponseEntity<CurrencyExchangeResponseDto> createCurrencyExchange(@RequestBody CurrencyExchangeRequestDto currencyExchangeRequestDto) {
        return ResponseEntity.ok(currencyExchangeService.save(currencyExchangeRequestDto));
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<CurrencyExchangeResponseDto>> findByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(currencyExchangeService.findByUserId(userId));
    }

    @PutMapping("{id}/cancel")
    public ResponseEntity<String> cancelCurrencyExchange(@PathVariable Long id) {
        currencyExchangeService.cancel(id);
        return ResponseEntity.ok("환전 요청이 취소되었습니다.");
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteCurrencyExchange(@PathVariable Long userId) {
        currencyExchangeService.deleteByUserId(userId);
        return ResponseEntity.ok("고객과 관련된 환전 요청이 삭제되었습니다.");
    }

}
