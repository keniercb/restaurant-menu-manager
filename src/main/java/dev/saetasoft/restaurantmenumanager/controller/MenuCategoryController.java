package dev.saetasoft.restaurantmenumanager.controller;

import dev.saetasoft.restaurantmenumanager.model.dto.request.MenuCategoryRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.MenuCategoryResponseDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.service.MenuCategoryService;
import dev.saetasoft.restaurantmenumanager.utils.PaginationUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu-category")
@Tag(name = "Menu Category")
public class MenuCategoryController {

    private final MenuCategoryService menuCategoryService;

    @GetMapping
    public ResponseEntity<PaginatedResponse<MenuCategoryResponseDto>> getMenuCategories(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "perPage", defaultValue = "10") Integer perPage,
            @RequestParam(name = "sortBy", defaultValue = "displayOrder") String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir
    ) {
        return ResponseEntity.ok(menuCategoryService.getAllCategories(
                PaginationUtils.params(page, perPage, sortBy, sortDir)
        ));
    }

    @PostMapping
    public ResponseEntity<MenuCategoryResponseDto> createCategory(
            @Valid @RequestBody MenuCategoryRequestDto categoryRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuCategoryService.createCategory(categoryRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Long id
    ) {
        menuCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
