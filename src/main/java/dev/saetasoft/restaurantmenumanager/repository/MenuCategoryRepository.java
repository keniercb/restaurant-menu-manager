package dev.saetasoft.restaurantmenumanager.repository;

import dev.saetasoft.restaurantmenumanager.model.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
    Optional<MenuCategory> findByName(String name);

    Optional<MenuCategory> findMenuCategoryByNameAndIdIsNot(String name, Long id);
}
