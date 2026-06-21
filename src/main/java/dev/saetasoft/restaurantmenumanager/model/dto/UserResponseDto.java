package dev.saetasoft.restaurantmenumanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String email;
    private String fullName;
}
