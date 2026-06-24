package dev.saetasoft.restaurantmenumanager.controller;

import dev.saetasoft.restaurantmenumanager.model.dto.request.MenuItemRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.MenuItemResponseDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.filter.MenuItemFilter;
import dev.saetasoft.restaurantmenumanager.service.MenuItemService;
import dev.saetasoft.restaurantmenumanager.utils.PaginationUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu-item")
@Tag(name = "Menu Item")
public class MenuItemController {
    private final MenuItemService menuItemService;

    @GetMapping
    public ResponseEntity<PaginatedResponse<MenuItemResponseDto>> getAllMenuItems(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer perPage,
            @RequestParam(defaultValue = "name") String orderBy,
            @RequestParam(defaultValue = "asc") String orderDir,
            MenuItemFilter itemFilter
    ) {
        return ResponseEntity.ok(menuItemService.getAllMenuItems(
                PaginationUtils.params(page, perPage, orderBy, orderDir), itemFilter
        ));
    }

    @PostMapping
    public ResponseEntity<MenuItemResponseDto> createMenuItem(
            @Valid @RequestBody MenuItemRequestDto menuItemRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                menuItemService.createMenuItem(menuItemRequest)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}
