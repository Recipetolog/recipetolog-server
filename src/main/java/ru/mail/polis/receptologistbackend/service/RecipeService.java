package ru.mail.polis.receptologistbackend.service;

import org.springframework.stereotype.Service;
import ru.mail.polis.receptologistbackend.domain.Ingredient;
import ru.mail.polis.receptologistbackend.domain.Recipe;
import ru.mail.polis.receptologistbackend.repository.IngredientRepository;
import ru.mail.polis.receptologistbackend.repository.RecipeRepository;

import java.util.List;
import java.util.Set;

@Service
public class RecipeService {
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Recipe getRecipeById(long id) {
        return recipeRepository.findById(id);
    }

/*    public List<Recipe> findWhichContainTheseIngredients(Set<Ingredient> ingredients) {

    }

    public List<Recipe> findWhichContainAllTheseIngredients(Set<Ingredient> ingredients) {

    }

    public List<Recipe> findWhichContainOnlyTheseIngredients(Set<Ingredient> ingredients) {

    }*/
}
