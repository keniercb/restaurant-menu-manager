package dev.saetasoft.restaurantmenumanager.service;

import dev.saetasoft.restaurantmenumanager.model.dto.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.dto.UserRegistrationDto;
import dev.saetasoft.restaurantmenumanager.model.dto.UserResponseDto;
import dev.saetasoft.restaurantmenumanager.model.entity.User;

public interface UserService {
    UserResponseDto register(UserRegistrationDto userRegistrationDto);

    PaginatedResponse<UserResponseDto> getUsers(PaginationParams paginationParams);

    void deleteUser(Long id);

    User getUserById(Long id);

    UserResponseDto mapToDto(User user);
}
