package dev.saetasoft.restaurantmenumanager.repository;

import dev.saetasoft.restaurantmenumanager.model.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
