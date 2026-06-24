package dev.saetasoft.restaurantmenumanager.model.specification;

import dev.saetasoft.restaurantmenumanager.model.entity.MenuItem;
import dev.saetasoft.restaurantmenumanager.model.filter.MenuItemFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class MenuItemSpecification {
    public static Specification<MenuItem> withFilters(MenuItemFilter itemFilter
    ) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (itemFilter.getName() != null && !itemFilter.getName().isBlank()) {
                String pattern = "%" + itemFilter.getName().toLowerCase() + "%";
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), pattern));
            }
            if (itemFilter.getRestaurantId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("restaurant").get("id"), itemFilter.getRestaurantId()));
            }
            if (itemFilter.getCategoryId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("category").get("id"), itemFilter.getCategoryId()));
            }
            if (itemFilter.getMinPrice() != null) {
                predicates.add(criteriaBuilder.ge(root.get("price"), itemFilter.getMinPrice()));
            }
            if (itemFilter.getMaxPrice() != null) {
                predicates.add(criteriaBuilder.le(root.get("price"), itemFilter.getMaxPrice()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
