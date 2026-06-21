package dev.saetasoft.restaurantmenumanager.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuCategoryRequestDto {
    @NotBlank(message = "Category name is required")
    @Size(max = 80, message = "Name can not exceed 80 characters")
    private String name;

    @Size(max = 255, message = "Name can not exceed 255 characters")
    private String description;
    @NotNull(message = "Menu Category display order is required")
    private Integer displayOrder;
}
