package dev.saetasoft.restaurantmenumanager.service;

import dev.saetasoft.restaurantmenumanager.model.dto.request.CuisineRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.request.CuisineResponseDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.entity.Cuisine;

public interface CuisineService {
    CuisineResponseDto createCuisine(CuisineRequestDto cuisineRequestDto);

    PaginatedResponse<CuisineResponseDto> getAllCuisines(PaginationParams paginationParams);

    CuisineResponseDto updateCuisine(CuisineRequestDto cuisineRequestDto, Long id);

    void deleteCuisines(Long id);

    Cuisine getCuisineById(Long id);

    CuisineResponseDto mapToDto(Cuisine cuisine);
}
