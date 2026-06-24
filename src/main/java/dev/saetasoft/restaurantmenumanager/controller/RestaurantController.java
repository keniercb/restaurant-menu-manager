package dev.saetasoft.restaurantmenumanager.controller;

import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.request.RestaurantRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.RestaurantResponseDto;
import dev.saetasoft.restaurantmenumanager.model.filter.RestaurantFilter;
import dev.saetasoft.restaurantmenumanager.service.RestaurantService;
import dev.saetasoft.restaurantmenumanager.utils.PaginationUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurant")
@Tag(name = "Restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantResponseDto> createRestaurant(
            @Valid @RequestBody RestaurantRequestDto restaurantRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.createRestaurant(restaurantRequestDto));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<RestaurantResponseDto>> getRestaurants(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer perPage,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            RestaurantFilter filter
    ) {
        return ResponseEntity.ok(
                restaurantService.getAllRestaurants(
                        PaginationUtils.params(page, perPage, sortBy, sortDir), filter));
    }
}
