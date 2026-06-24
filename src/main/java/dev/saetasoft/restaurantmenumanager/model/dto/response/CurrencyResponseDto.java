package dev.saetasoft.restaurantmenumanager.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyResponseDto {
    private Long id;
    private String currencyCode;
    private String description;
}
