package dev.saetasoft.restaurantmenumanager.model.filter;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestaurantFilter {
    private String searchText;
    private Long ownerId;
    private Long cuisineTypeId;
}
