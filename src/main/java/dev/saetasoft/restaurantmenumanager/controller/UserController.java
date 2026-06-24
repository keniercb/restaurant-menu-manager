package dev.saetasoft.restaurantmenumanager.controller;

import dev.saetasoft.restaurantmenumanager.model.dto.response.PaginatedResponse;
import dev.saetasoft.restaurantmenumanager.model.dto.request.UserRegistrationDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.UserResponseDto;
import dev.saetasoft.restaurantmenumanager.service.UserService;
import dev.saetasoft.restaurantmenumanager.utils.PaginationUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User management API")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRegistrationDto registrationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(registrationDto));
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<UserResponseDto>> getUsers(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer perPage,
            @RequestParam(defaultValue = "email") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return ResponseEntity.ok(userService.getUsers(
                PaginationUtils.params(page, perPage, sortBy, sortDir)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
