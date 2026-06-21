package dev.saetasoft.restaurantmenumanager.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequestDto {
    @NotBlank
    @Size(max = 100)
    private String name;
    @Size(max = 255)
    private String address;
    @Size(max = 10)
    private String phone;
    @Size(max = 100)
    private String email;
    @NonNull
    private Long cuisineId;
}
