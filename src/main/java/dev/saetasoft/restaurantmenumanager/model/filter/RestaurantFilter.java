package dev.saetasoft.restaurantmenumanager.model.filter;

import lombok.Data;

@Data
public class RestaurantFilter {
    private String searchText;
    private Long ownerId;
    private Long cuisineTypeId;
}
