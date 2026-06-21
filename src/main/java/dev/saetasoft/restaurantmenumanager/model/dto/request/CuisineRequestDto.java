package dev.saetasoft.restaurantmenumanager.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuisineRequestDto {
    @NotBlank(message = "Cuisine name is required")
    @Size(max = 100, message = "Cuisine name could not exceed 100 characters")
    private String name;
    private String description;
}
