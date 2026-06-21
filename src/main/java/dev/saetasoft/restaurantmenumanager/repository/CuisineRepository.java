package dev.saetasoft.restaurantmenumanager.repository;

import dev.saetasoft.restaurantmenumanager.model.entity.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long> {
    Optional<Cuisine> findCuisineByName(String name);

    List<Cuisine> findCuisineByNameAndIdIsNot(String name, Long id);
}
