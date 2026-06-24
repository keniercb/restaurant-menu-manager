package dev.saetasoft.restaurantmenumanager.service.impl;

import dev.saetasoft.restaurantmenumanager.model.dto.request.LoginRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.AuthResponseDto;
import dev.saetasoft.restaurantmenumanager.model.entity.User;
import dev.saetasoft.restaurantmenumanager.repository.UserRepository;
import dev.saetasoft.restaurantmenumanager.service.AuthService;
import dev.saetasoft.restaurantmenumanager.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return AuthResponseDto.builder()
                .token(jwtService.generateToken(customUserDetailsService.loadUserByUsername(request.getEmail())))
                .type("Bearer")
                .userId(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }
}
