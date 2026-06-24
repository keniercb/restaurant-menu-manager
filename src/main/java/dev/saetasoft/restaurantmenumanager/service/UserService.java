package dev.saetasoft.restaurantmenumanager.service;

import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.dto.request.UserRegistrationDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.UserResponseDto;
import dev.saetasoft.restaurantmenumanager.model.entity.User;

public interface UserService {
    UserResponseDto createUser(UserRegistrationDto userRegistrationDto);

    PaginatedResponse<UserResponseDto> getUsers(PaginationParams paginationParams);

    void deleteUser(Long id);

    User getUserById(Long id);

    UserResponseDto mapToDto(User user);
}
