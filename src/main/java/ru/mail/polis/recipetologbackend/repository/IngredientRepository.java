package ru.mail.polis.recipetologbackend.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mail.polis.recipetologbackend.domain.Ingredient;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Ingredient findById(long id);

    Ingredient findByName(String name);

    List<Ingredient> findAll();
}
