package dev.saetasoft.restaurantmenumanager.service.impl;

import dev.saetasoft.restaurantmenumanager.model.dto.request.MenuCategoryRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.MenuCategoryResponseDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.entity.MenuCategory;
import dev.saetasoft.restaurantmenumanager.repository.MenuCategoryRepository;
import dev.saetasoft.restaurantmenumanager.service.MenuCategoryService;
import dev.saetasoft.restaurantmenumanager.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuCategoryServiceImpl implements MenuCategoryService {
    private final MenuCategoryRepository menuCategoryRepository;

    @Override
    public PaginatedResponse<MenuCategoryResponseDto> getAllCategories(PaginationParams paginationParams) {
        Page<MenuCategory> categories = menuCategoryRepository.findAll(PaginationUtils.of(paginationParams));
        return PaginationUtils.response(categories.map(this::mapToDto));
    }

    @Override
    public MenuCategoryResponseDto createCategory(MenuCategoryRequestDto menuCategoryRequest) {
        if (menuCategoryRepository.findByName(menuCategoryRequest.getName()).isPresent()) {
            throw new RuntimeException("Menu category name already exists.");
        }
        MenuCategory created = mapFromDto(menuCategoryRequest);
        menuCategoryRepository.save(created);
        return mapToDto(created);
    }

    private MenuCategory mapFromDto(MenuCategoryRequestDto menuCategoryRequest) {
        return MenuCategory.builder()
                .name(menuCategoryRequest.getName())
                .description(menuCategoryRequest.getDescription())
                .displayOrder(menuCategoryRequest.getDisplayOrder())
                .build();
    }

    public MenuCategoryResponseDto mapToDto(MenuCategory menuCategory) {
        return MenuCategoryResponseDto.builder()
                .name(menuCategory.getName())
                .description(menuCategory.getDescription())
                .id(menuCategory.getId())
                .displayOrder(menuCategory.getDisplayOrder())
                .created(menuCategory.getCreatedAt())
                .updated(menuCategory.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteCategory(Long id) {
        menuCategoryRepository.delete(getMenuCategoryById(id));
    }

    public MenuCategory getMenuCategoryById(Long id) {
        return menuCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu category not found."));
    }

    @Override
    public MenuCategoryResponseDto updateCategory(Long menuCategoryId, MenuCategoryRequestDto menuCategoryRequest) {
        MenuCategory founded = getMenuCategoryById(menuCategoryId);
        Optional<MenuCategory> namedCategory = menuCategoryRepository.findByName(menuCategoryRequest.getName());
        if (namedCategory.isPresent() && !Objects.equals(founded.getId(), namedCategory.get().getId())) {
            throw new RuntimeException("Category name could not be changed, is already taken.");
        }
        founded.setName(menuCategoryRequest.getName());
        founded.setDescription(menuCategoryRequest.getDescription());
        founded.setDisplayOrder(menuCategoryRequest.getDisplayOrder());
        menuCategoryRepository.save(founded);
        return mapToDto(founded);
    }
}
