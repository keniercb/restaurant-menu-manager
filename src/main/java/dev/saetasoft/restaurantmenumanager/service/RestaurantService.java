package dev.saetasoft.restaurantmenumanager.service;

import dev.saetasoft.restaurantmenumanager.model.dto.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.dto.RestaurantRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.RestaurantResponseDto;
import dev.saetasoft.restaurantmenumanager.model.filter.RestaurantFilter;

public interface RestaurantService {
    RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurantRequestDto, Long ownerId);

    PaginatedResponse<RestaurantResponseDto> getRestaurantsByOwner(PaginationParams paginationParams, Long ownerId);

    PaginatedResponse<RestaurantResponseDto> getAllRestaurants(PaginationParams paginationParams);

    PaginatedResponse<RestaurantResponseDto> getAllRestaurants(PaginationParams paginationParams, RestaurantFilter filter);


}
