package dev.saetasoft.restaurantmenumanager.service.impl;

import dev.saetasoft.restaurantmenumanager.model.dto.CuisineRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.CuisineResponseDto;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.entity.Cuisine;
import dev.saetasoft.restaurantmenumanager.repository.CuisineRepository;
import dev.saetasoft.restaurantmenumanager.service.CuisineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuisineServiceImpl implements CuisineService {
    private final CuisineRepository cuisineRepository;

    @Override
    public CuisineResponseDto createCuisine(CuisineRequestDto cuisineRequestDto) {
        if (cuisineRepository.findCuisineByName(cuisineRequestDto.getName()).isPresent()) {
            throw new RuntimeException("Cuisine already exists");
        }
        return mapToDto(cuisineRepository.save(mapFromDto(cuisineRequestDto)));
    }


    @Override
    public PaginatedResponse<CuisineResponseDto> getAllCuisines(PaginationParams paginationParams) {
        Sort sort = paginationParams.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(paginationParams.getSortBy()).ascending()
                : Sort.by(paginationParams.getSortBy()).descending();
        Pageable pageable = PageRequest.of(paginationParams.getPage(), paginationParams.getSize(), sort);
        Page<Cuisine> cuisinePage = cuisineRepository.findAll(pageable);
        List<CuisineResponseDto> cuisines = cuisinePage.map(this::mapToDto).stream().toList();
        return PaginatedResponse.<CuisineResponseDto>builder()
                .perPage(paginationParams.getSize())
                .results(cuisines.size())
                .totalResults(cuisinePage.getTotalElements())
                .page(paginationParams.getPage() + 1)
                .totalPages(cuisinePage.getTotalPages())
                .data(cuisines)
                .build();
    }

    @Override
    public CuisineResponseDto updateCuisine(CuisineRequestDto cuisineRequestDto, Long id) {
        Cuisine cuisine = cuisineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuisine not found"));
        if (!cuisineRepository.findCuisineByNameAndIdIsNot(cuisineRequestDto.getName(), id).isEmpty())
            throw new RuntimeException("Cuisine name is already taken");
        cuisine.setName(cuisineRequestDto.getName());
        cuisine.setDescription(cuisineRequestDto.getDescription());
        cuisineRepository.save(cuisine);
        return mapToDto(cuisine);
    }

    @Override
    public void deleteCuisines(Long id) {
        cuisineRepository.delete(getCuisineById(id));
    }

    @Override
    public Cuisine getCuisineById(Long id) {
        return cuisineRepository.findById(id).orElseThrow(() -> new RuntimeException("Cuisine not found"));
    }


    public CuisineResponseDto mapToDto(Cuisine cuisine) {
        return CuisineResponseDto.builder()
                .id(cuisine.getId())
                .name(cuisine.getName())
                .updated(cuisine.getUpdatedAt())
                .created(cuisine.getCreatedAt())
                .description(cuisine.getDescription())
                .build();
    }

    private Cuisine mapFromDto(CuisineRequestDto cuisineRequestDto) {

        return Cuisine.builder()
                .name(cuisineRequestDto.getName())
                .description(cuisineRequestDto.getDescription())
                .build();
    }
}
