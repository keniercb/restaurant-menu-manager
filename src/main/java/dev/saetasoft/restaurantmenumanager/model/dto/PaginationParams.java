package dev.saetasoft.restaurantmenumanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationParams {
    private int page;
    private int size;
    private String sortBy;
    private String sortDir;
}
