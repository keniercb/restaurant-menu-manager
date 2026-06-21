package dev.saetasoft.restaurantmenumanager.service;

import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.dto.request.MenuItemRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.MenuItemResponseDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;

public interface MenuItemService {
    MenuItemResponseDto createMenuItem(MenuItemRequestDto menuItemRequestDto);

    MenuItemResponseDto updateMenuItem(Long id, MenuItemRequestDto menuItemRequestDto);

    PaginatedResponse<MenuItemResponseDto> getAllMenuItems(PaginationParams paginationParams);

    void deleteMenuItem(Long id);

}
