package dev.saetasoft.restaurantmenumanager.model.filter;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuItemFilter {
    private Long restaurantId;
    private Long categoryId;
    private String name;

    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
