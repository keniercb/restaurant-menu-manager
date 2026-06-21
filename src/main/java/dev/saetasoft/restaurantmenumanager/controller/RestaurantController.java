package dev.saetasoft.restaurantmenumanager.controller;

import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.dto.request.RestaurantRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.RestaurantResponseDto;
import dev.saetasoft.restaurantmenumanager.model.filter.RestaurantFilter;
import dev.saetasoft.restaurantmenumanager.service.RestaurantService;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.createRestaurant(restaurantRequestDto, Integer.toUnsignedLong(2)));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<RestaurantResponseDto>> getRestaurants(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer perPage,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            RestaurantFilter filter
    ) {
        PaginationParams paginationParams = PaginationParams.builder()
                .sortBy(sortBy)
                .sortDir(sortDir)
                .page(Integer.max(0, page - 1))
                .size(Integer.max(1, perPage))
                .build();
        return ResponseEntity.ok(
                restaurantService.getAllRestaurants(paginationParams, filter)
        );
    }
}
