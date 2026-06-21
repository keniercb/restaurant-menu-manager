package dev.saetasoft.restaurantmenumanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private CuisineResponseDto cuisine;
    private UserResponseDto owner;
    private LocalDateTime created;
    private  LocalDateTime updated;
}
