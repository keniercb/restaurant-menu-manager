package dev.saetasoft.restaurantmenumanager.repository;

import dev.saetasoft.restaurantmenumanager.model.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency>  getCurrencyById(Long id);
}
