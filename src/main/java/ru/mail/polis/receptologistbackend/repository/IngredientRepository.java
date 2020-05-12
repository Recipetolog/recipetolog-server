package ru.mail.polis.receptologistbackend.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mail.polis.receptologistbackend.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Ingredient findById(long id);

    Ingredient findByName(String name);
}
