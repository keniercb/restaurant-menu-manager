package dev.saetasoft.restaurantmenumanager.model.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Menu item name is required")
    @Size(max = 200, message = "Menu item name could not exceeds 200 characters")
    private String name;
    @Size(max = 500, message = "Menu item description could not exceeds 500 characters")
    private String description;
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
    private Boolean isAvailable;
    @NotNull(message = "Restaurant id is required")
    private Long restaurantId;
    @NotNull(message = "Category id is required")
    private Long categoryId;
    private Integer displayOrder;
}
