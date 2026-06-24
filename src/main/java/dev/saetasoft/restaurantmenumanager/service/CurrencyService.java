package dev.saetasoft.restaurantmenumanager.service;

import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.dto.request.CurrencyRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.CurrencyResponseDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.entity.Currency;
import lombok.NonNull;

public interface CurrencyService {
    CurrencyResponseDto createCurrency(CurrencyRequestDto currencyRequestDto);

    CurrencyResponseDto updateCurrency(Long id, CurrencyRequestDto currencyRequestDto);

    void delete(Long id);

    PaginatedResponse<CurrencyResponseDto> getAllCurrencies(PaginationParams params);

    Currency gerCurrencyById(@NonNull Long currencyId);
}
