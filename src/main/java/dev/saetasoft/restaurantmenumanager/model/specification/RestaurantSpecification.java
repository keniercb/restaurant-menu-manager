package dev.saetasoft.restaurantmenumanager.model.specification;

import dev.saetasoft.restaurantmenumanager.model.entity.Restaurant;
import dev.saetasoft.restaurantmenumanager.model.filter.RestaurantFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RestaurantSpecification {
    public static Specification<Restaurant> withFilters(
            RestaurantFilter filter
    ) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getSearchText() != null && !filter.getSearchText().isBlank()) {
                String pattern = "%" + filter.getSearchText().toLowerCase() + "%";
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), pattern);
                predicates.add(namePredicate);
            }
            if (filter.getOwnerId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("owner").get("id"), filter.getOwnerId()));
            }
            if (filter.getCuisineTypeId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("cuisineType").get("id"), filter.getCuisineTypeId()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
