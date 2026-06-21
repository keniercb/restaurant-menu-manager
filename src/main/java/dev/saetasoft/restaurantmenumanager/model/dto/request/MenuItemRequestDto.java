package dev.saetasoft.restaurantmenumanager.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isAvailable;
    private Long restaurantId;
    private Long categoryId;
    private Integer displayOrder;
}
