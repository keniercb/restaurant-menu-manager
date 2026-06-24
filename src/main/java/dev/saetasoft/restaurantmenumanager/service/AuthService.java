package dev.saetasoft.restaurantmenumanager.service;

import dev.saetasoft.restaurantmenumanager.model.dto.request.LoginRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.AuthResponseDto;

public interface AuthService {
    AuthResponseDto login(LoginRequestDto request);
}
