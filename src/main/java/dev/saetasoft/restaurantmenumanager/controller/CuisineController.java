package dev.saetasoft.restaurantmenumanager.controller;

import dev.saetasoft.restaurantmenumanager.model.dto.request.CuisineRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.request.CuisineResponseDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.service.CuisineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/cuisine-type")
@RestController
@RequiredArgsConstructor
@Tag(name = "Cuisine", description = "Cuisines management")
public class CuisineController {

    private final CuisineService cuisineService;

    @PostMapping
    public ResponseEntity<CuisineResponseDto> createCuisine(@Valid @RequestBody CuisineRequestDto cuisineRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cuisineService.createCuisine(cuisineRequestDto));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<CuisineResponseDto>> getCuisines(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int perPage,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        PaginationParams paginationParams = PaginationParams.builder()
                .size(perPage)
                .page(Integer.max(0, page - 1))
                .size(Integer.max(1, perPage))
                .sortBy(sortBy)
                .sortDir(sortDir)
                .build();
        return ResponseEntity.ok(cuisineService.getAllCuisines(paginationParams));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuisineResponseDto> updateCuisine(
            @PathVariable Long id,
            @Valid @RequestBody CuisineRequestDto cuisineRequestDto
    ) {
        return ResponseEntity.ok(cuisineService.updateCuisine(cuisineRequestDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuisine(@PathVariable Long id) {
        cuisineService.deleteCuisines(id);
        return ResponseEntity.noContent().build();
    }
}
