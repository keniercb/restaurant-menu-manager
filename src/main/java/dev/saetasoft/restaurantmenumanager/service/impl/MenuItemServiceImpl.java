package dev.saetasoft.restaurantmenumanager.service.impl;

import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.dto.request.MenuItemRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.MenuItemResponseDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.entity.MenuItem;
import dev.saetasoft.restaurantmenumanager.model.filter.MenuItemFilter;
import dev.saetasoft.restaurantmenumanager.model.specification.MenuItemSpecification;
import dev.saetasoft.restaurantmenumanager.repository.MenuItemRepository;
import dev.saetasoft.restaurantmenumanager.service.MenuCategoryService;
import dev.saetasoft.restaurantmenumanager.service.MenuItemService;
import dev.saetasoft.restaurantmenumanager.service.RestaurantService;
import dev.saetasoft.restaurantmenumanager.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final RestaurantService restaurantService;
    private final MenuCategoryService menuCategoryService;

    @Override
    @Transactional
    public MenuItemResponseDto createMenuItem(MenuItemRequestDto menuItemRequestDto) {
        if (!menuItemRepository.findMenuItemByNameAndRestaurantIs(menuItemRequestDto.getName(),
                restaurantService.getRestaurantById(menuItemRequestDto.getRestaurantId())).isEmpty()) {
            throw new RuntimeException("A menu item with the provided name already exists");
        }
        MenuItem created = mapFromDto(menuItemRequestDto);
        created.setRestaurant(restaurantService.getRestaurantById(menuItemRequestDto.getRestaurantId()));
        created.setMenuCategory(menuCategoryService.getMenuCategoryById(menuItemRequestDto.getCategoryId()));
        menuItemRepository.save(created);
        return mapToDto(created);
    }

    private MenuItem mapFromDto(MenuItemRequestDto menuItemRequestDto) {
        return MenuItem.builder()
                .name(menuItemRequestDto.getName())
                .description(menuItemRequestDto.getDescription())
                .price(menuItemRequestDto.getPrice())
                .isAvailable(menuItemRequestDto.getIsAvailable())
                .displayOrder(menuItemRequestDto.getDisplayOrder())
                .build();
    }

    @Override
    @Transactional
    public MenuItemResponseDto updateMenuItem(Long id, MenuItemRequestDto menuItemRequestDto) {
        MenuItem menuItem = getMenuItemById(id);
        menuItem.setPrice(menuItemRequestDto.getPrice());
        menuItem.setName(menuItemRequestDto.getName());
        menuItem.setIsAvailable(menuItemRequestDto.getIsAvailable());
        menuItemRepository.save(menuItem);
        return mapToDto(menuItem);
    }

    @Override
    public PaginatedResponse<MenuItemResponseDto> getAllMenuItems(PaginationParams paginationParams, MenuItemFilter menuItemFilter) {
        Page<MenuItem> menuItemPage = menuItemRepository.findAll(MenuItemSpecification.withFilters(menuItemFilter), PaginationUtils.of(paginationParams));
        return PaginationUtils.response(menuItemPage.map(this::mapToDto));
    }

    private MenuItemResponseDto mapToDto(MenuItem menuItem) {
        return MenuItemResponseDto.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .restaurant(restaurantService.mapToDto(menuItem.getRestaurant()))
                .category(menuCategoryService.mapToDto(menuItem.getMenuCategory()))
                .created(menuItem.getCreatedAt())
                .updated(menuItem.getUpdatedAt())
                .build();
    }

    @Override
    @Transactional
    public void deleteMenuItem(Long id) {
        menuItemRepository.delete(getMenuItemById(id));
    }

    private MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu item not found"));
    }
}
