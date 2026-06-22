package dev.saetasoft.restaurantmenumanager.service;

import dev.saetasoft.restaurantmenumanager.model.dto.request.MenuCategoryRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.MenuCategoryResponseDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.entity.MenuCategory;

public interface MenuCategoryService {
    PaginatedResponse<MenuCategoryResponseDto> getAllCategories(PaginationParams paginationParams);

    MenuCategoryResponseDto createCategory(MenuCategoryRequestDto menuCategoryRequest);

    void deleteCategory(Long id);

    MenuCategoryResponseDto updateCategory(Long menuCategoryId, MenuCategoryRequestDto menuCategoryRequest);

    MenuCategory getMenuCategoryById(Long id);
}
