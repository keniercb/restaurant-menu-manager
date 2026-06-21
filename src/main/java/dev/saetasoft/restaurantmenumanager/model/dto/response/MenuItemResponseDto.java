package dev.saetasoft.restaurantmenumanager.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private RestaurantResponseDto restaurant;
    private MenuCategoryResponseDto category;
    private LocalDateTime created;
    private LocalDateTime updated;
}
