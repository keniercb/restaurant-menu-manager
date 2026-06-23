package dev.saetasoft.restaurantmenumanager.service;

import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.dto.request.RestaurantRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.RestaurantResponseDto;
import dev.saetasoft.restaurantmenumanager.model.entity.Restaurant;
import dev.saetasoft.restaurantmenumanager.model.filter.RestaurantFilter;
import jakarta.validation.constraints.NotNull;

public interface RestaurantService {
    RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurantRequestDto, Long ownerId);

    PaginatedResponse<RestaurantResponseDto> getRestaurantsByOwner(PaginationParams paginationParams, Long ownerId);

    PaginatedResponse<RestaurantResponseDto> getAllRestaurants(PaginationParams paginationParams);

    PaginatedResponse<RestaurantResponseDto> getAllRestaurants(PaginationParams paginationParams, RestaurantFilter filter);

    Restaurant getRestaurantById(Long restaurantId);

    RestaurantResponseDto mapToDto(Restaurant restaurant);
}
