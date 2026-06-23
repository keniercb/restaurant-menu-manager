package dev.saetasoft.restaurantmenumanager.service.impl;

import dev.saetasoft.restaurantmenumanager.model.dto.request.CuisineRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.request.CuisineResponseDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.entity.Cuisine;
import dev.saetasoft.restaurantmenumanager.repository.CuisineRepository;
import dev.saetasoft.restaurantmenumanager.service.CuisineService;
import dev.saetasoft.restaurantmenumanager.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
        Page<Cuisine> cuisinePage = cuisineRepository.findAll(PaginationUtils.of(paginationParams));
        return PaginationUtils.response(cuisinePage.map(this::mapToDto));
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
