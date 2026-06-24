package dev.saetasoft.restaurantmenumanager.service.impl;

import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.dto.request.RestaurantRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.RestaurantResponseDto;
import dev.saetasoft.restaurantmenumanager.model.entity.Cuisine;
import dev.saetasoft.restaurantmenumanager.model.entity.Restaurant;
import dev.saetasoft.restaurantmenumanager.model.entity.User;
import dev.saetasoft.restaurantmenumanager.model.filter.RestaurantFilter;
import dev.saetasoft.restaurantmenumanager.model.specification.RestaurantSpecification;
import dev.saetasoft.restaurantmenumanager.repository.RestaurantRepository;
import dev.saetasoft.restaurantmenumanager.service.CuisineService;
import dev.saetasoft.restaurantmenumanager.service.RestaurantService;
import dev.saetasoft.restaurantmenumanager.service.UserService;
import dev.saetasoft.restaurantmenumanager.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    protected final RestaurantRepository restaurantRepository;
    private final CuisineService cuisineService;
    private final UserService userService;

    @Override
    @Transactional
    public RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurantRequestDto, Long ownerId) {
        User owner = userService.getUserById(ownerId);
        Cuisine cuisine = cuisineService.getCuisineById(restaurantRequestDto.getCuisineId());
        Restaurant restaurant = mapFromDto(restaurantRequestDto);
        restaurant.setOwner(owner);
        restaurant.setCuisineType(cuisine);
        restaurantRepository.save(restaurant);
        return mapToDto(restaurant);
    }


    @Override
    public PaginatedResponse<RestaurantResponseDto> getRestaurantsByOwner(PaginationParams paginationParams, Long ownerId) {
        return null;
    }

    @Override
    public PaginatedResponse<RestaurantResponseDto> getAllRestaurants(PaginationParams paginationParams) {
        Page<Restaurant> restaurantPage = restaurantRepository.findAll(PaginationUtils.of(paginationParams));
        return PaginationUtils.response(restaurantPage.map(this::mapToDto));
    }

    @Override
    public PaginatedResponse<RestaurantResponseDto> getAllRestaurants(PaginationParams paginationParams, RestaurantFilter filter) {
        Page<Restaurant> restaurantPage = restaurantRepository.findAll(RestaurantSpecification.withFilters(filter), PaginationUtils.of(paginationParams));
        return PaginationUtils.response(restaurantPage.map(this::mapToDto));
    }

    @Override
    public Restaurant getRestaurantById(Long restaurantId) {
        return restaurantRepository.getRestaurantsById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    private Restaurant mapFromDto(RestaurantRequestDto restaurantRequestDto) {
        return Restaurant.builder()
                .name(restaurantRequestDto.getName())
                .address(restaurantRequestDto.getAddress())
                .phone(restaurantRequestDto.getPhone())
                .email(restaurantRequestDto.getEmail())
                .build();
    }

    public RestaurantResponseDto mapToDto(Restaurant restaurant) {
        return RestaurantResponseDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .cuisine(cuisineService.mapToDto(restaurant.getCuisineType()))
                .owner(userService.mapToDto(restaurant.getOwner()))
                .address(restaurant.getAddress())
                .created(restaurant.getCreatedAt())
                .updated(restaurant.getUpdatedAt())
                .currency(restaurant.getCurrency().getIsoCode())
                .build();
    }
}
