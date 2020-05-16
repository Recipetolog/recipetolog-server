package ru.mail.polis.recipetologbackend.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mail.polis.recipetologbackend.domain.Recipe;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Recipe findById(long id);

    List<Recipe> findByNameContaining(String name);
}
