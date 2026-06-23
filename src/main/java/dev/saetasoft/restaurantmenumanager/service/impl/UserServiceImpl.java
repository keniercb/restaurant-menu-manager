package dev.saetasoft.restaurantmenumanager.service.impl;

import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.dto.request.UserRegistrationDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.UserResponseDto;
import dev.saetasoft.restaurantmenumanager.model.entity.User;
import dev.saetasoft.restaurantmenumanager.repository.UserRepository;
import dev.saetasoft.restaurantmenumanager.service.UserService;
import dev.saetasoft.restaurantmenumanager.utils.PaginationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto register(UserRegistrationDto userRegistrationDto) {
        if (userRepository.findUserByEmail(userRegistrationDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already taken");
        }
        return mapToDto(userRepository.save(mapFromDto(userRegistrationDto)));
    }

    @Override
    public PaginatedResponse<UserResponseDto> getUsers(PaginationParams paginationParams) {
        Page<User> userPage = userRepository.findAll(PaginationUtils.of(paginationParams));
        return PaginationUtils.response(userPage.map(this::mapToDto));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(getUserById(id));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    private User mapFromDto(UserRegistrationDto userRegistrationDto) {
        return User.builder()
                .fullName(userRegistrationDto.getFullName())
                .email(userRegistrationDto.getEmail())
                .password(userRegistrationDto.getPassword())
                .build();
    }

    public UserResponseDto mapToDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .build();
    }
}
