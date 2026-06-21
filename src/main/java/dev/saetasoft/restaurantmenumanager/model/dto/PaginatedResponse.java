package dev.saetasoft.restaurantmenumanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponse<T> {
    private List<T> data;
    private Integer page;
    private Integer perPage;
    private Integer results;
    private Long totalResults;
    private Integer totalPages;

}
