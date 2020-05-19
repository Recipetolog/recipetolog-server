package ru.mail.polis.recipetologbackend.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mail.polis.recipetologbackend.domain.IngredientRecipe;
import ru.mail.polis.recipetologbackend.domain.IngredientRecipeId;

public interface IngredientRecipeRepository extends CrudRepository<IngredientRecipe, IngredientRecipeId> {

}
