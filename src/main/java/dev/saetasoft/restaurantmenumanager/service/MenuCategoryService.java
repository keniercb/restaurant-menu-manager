package dev.saetasoft.restaurantmenumanager.service;

import dev.saetasoft.restaurantmenumanager.model.dto.MenuCategoryRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.MenuCategoryResponseDto;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.entity.MenuCategory;

public interface MenuCategoryService {
    PaginatedResponse<MenuCategoryResponseDto> getAllCategories(PaginationParams paginationParams);

    MenuCategoryResponseDto createCategory(MenuCategoryRequestDto menuCategoryRequest);

    void deleteCategory(Long id);

    MenuCategoryResponseDto updateCategory(Long menuCategoryId, MenuCategoryRequestDto menuCategoryRequest);
}
