package dev.saetasoft.restaurantmenumanager.service;

import dev.saetasoft.restaurantmenumanager.model.dto.request.LoginRequestDto;
import dev.saetasoft.restaurantmenumanager.model.dto.response.AuthResponseDto;
import dev.saetasoft.restaurantmenumanager.model.entity.User;

public interface AuthService {
    AuthResponseDto login(LoginRequestDto request);

    String getCurrentUserEmail();

    User getCurrentUser();
}
