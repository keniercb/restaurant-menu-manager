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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    protected final RestaurantRepository restaurantRepository;
    private final CuisineService cuisineService;
    private final UserService userService;

    @Override
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
        Sort sort = paginationParams.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(paginationParams.getSortBy()).ascending()
                : Sort.by(paginationParams.getSortBy()).descending();
        Pageable pageable = PageRequest.of(paginationParams.getPage(), paginationParams.getSize(), sort);
        Page<Restaurant> restaurantPage = restaurantRepository.findAll(pageable);
        List<RestaurantResponseDto> restaurants = restaurantPage.map(this::mapToDto).stream().toList();
        return PaginatedResponse.<RestaurantResponseDto>builder()
                .results(restaurants.size())
                .perPage(paginationParams.getSize())
                .totalResults(restaurantPage.getTotalElements())
                .totalPages(restaurantPage.getTotalPages())
                .page(paginationParams.getPage() + 1)
                .data(restaurants)
                .build();
    }

    @Override
    public PaginatedResponse<RestaurantResponseDto> getAllRestaurants(PaginationParams paginationParams, RestaurantFilter filter) {
        Specification<Restaurant> specification = RestaurantSpecification.withFilters(filter);
        Sort sort = paginationParams.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(paginationParams.getSortBy()).ascending()
                : Sort.by(paginationParams.getSortBy()).descending();
        Pageable pageable = PageRequest.of(paginationParams.getPage(), paginationParams.getSize(), sort);
        Page<Restaurant> restaurantPage = restaurantRepository.findAll(specification, pageable);
        List<RestaurantResponseDto> restaurants = restaurantPage.map(this::mapToDto).stream().toList();
        return PaginatedResponse.<RestaurantResponseDto>builder()
                .results(restaurants.size())
                .perPage(paginationParams.getSize())
                .totalResults(restaurantPage.getTotalElements())
                .totalPages(restaurantPage.getTotalPages())
                .page(paginationParams.getPage() + 1)
                .data(restaurants)
                .build();
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

    private RestaurantResponseDto mapToDto(Restaurant restaurant) {
        return RestaurantResponseDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .cuisine(cuisineService.mapToDto(restaurant.getCuisineType()))
                .owner(userService.mapToDto(restaurant.getOwner()))
                .address(restaurant.getAddress())
                .created(restaurant.getCreatedAt())
                .updated(restaurant.getUpdatedAt())
                .build();
    }
}
