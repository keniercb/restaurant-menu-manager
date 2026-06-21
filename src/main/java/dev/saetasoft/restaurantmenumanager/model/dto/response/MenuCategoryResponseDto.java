package dev.saetasoft.restaurantmenumanager.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuCategoryResponseDto {
    private Long id;
    private String name;
    private String description;
    private Integer displayOrder;
    private LocalDateTime created;
    private LocalDateTime updated;
}
