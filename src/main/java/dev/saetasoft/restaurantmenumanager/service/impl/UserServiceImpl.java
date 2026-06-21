package dev.saetasoft.restaurantmenumanager.service.impl;

import dev.saetasoft.restaurantmenumanager.model.dto.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.PaginationParams;
import dev.saetasoft.restaurantmenumanager.model.dto.UserRegistrationDto;
import dev.saetasoft.restaurantmenumanager.model.dto.UserResponseDto;
import dev.saetasoft.restaurantmenumanager.model.entity.User;
import dev.saetasoft.restaurantmenumanager.repository.UserRepository;
import dev.saetasoft.restaurantmenumanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Sort sort = paginationParams.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(paginationParams.getSortBy()).ascending()
                : Sort.by(paginationParams.getSortBy()).descending();

        Pageable pageable = PageRequest.of(paginationParams.getPage(), paginationParams.getSize(), sort);
        Page<User> userPage = userRepository.findAll(pageable);
        List<UserResponseDto> userList = userPage.map(this::mapToDto).toList();
        return PaginatedResponse.<UserResponseDto>builder()
                .perPage(paginationParams.getSize())
                .page(paginationParams.getPage() + 1)
                .data(userList)
                .results(userPage.getNumberOfElements())
                .totalResults(userPage.getTotalElements())
                .totalPages(userPage.getTotalPages())
                .build();
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
