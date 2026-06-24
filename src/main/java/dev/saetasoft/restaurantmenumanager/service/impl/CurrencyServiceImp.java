package dev.saetasoft.restaurantmenumanager.service.impl;

import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.dto.request.CurrencyRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.CurrencyResponseDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.entity.Currency;
import dev.saetasoft.restaurantmenumanager.service.CurrencyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CurrencyServiceImp implements CurrencyService {
    @Override
    @Transactional
    public CurrencyResponseDto createCurrency(CurrencyRequestDto currencyRequestDto) {
        return null;
    }

    @Override
    @Transactional
    public CurrencyResponseDto updateCurrency(Long id, CurrencyRequestDto currencyRequestDto) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {

    }

    @Override
    public PaginatedResponse<CurrencyResponseDto> getAllCurrencies(PaginationParams params) {
        return null;
    }

    private CurrencyResponseDto mapToDto(Currency currency) {
        return CurrencyResponseDto.builder()
                .build();
    }

    private Currency mapFromDto(CurrencyRequestDto currencyRequestDto) {
        return Currency.builder()
                .build();
    }
}
