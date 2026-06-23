package dev.saetasoft.restaurantmenumanager.utils;

import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PaginationUtils {
    public static Pageable of(PaginationParams paginationParams) {
        int page = Math.max(0, paginationParams.getPage());
        int size = Math.max(1, paginationParams.getSize());
        Sort sort = paginationParams.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(paginationParams.getSortBy()).ascending()
                : Sort.by(paginationParams.getSortBy()).descending();
        return PageRequest.of(page, size, sort);
    }

    public static <T, I> PaginatedResponse<T> response(Page<I> page, List<T> list) {
        return PaginatedResponse.<T>builder()
                .perPage(page.getSize())
                .page(page.getNumber())
                .data(list)
                .results(page.getContent().size())
                .totalResults(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    public static PaginationParams params(int page, int perPage, String sortBy, String sortOrder) {
        int pageNumber = Math.max(0, page - 1);
        int size = Math.max(1, perPage);
        return PaginationParams.builder()
                .page(pageNumber)
                .size(size)
                .sortBy(sortBy)
                .sortDir(sortOrder)
                .build();
    }
}
