package ru.mail.polis.receptologistbackend.service;

import org.springframework.stereotype.Service;
import ru.mail.polis.receptologistbackend.domain.Ingredient;
import ru.mail.polis.receptologistbackend.domain.Recipe;
import ru.mail.polis.receptologistbackend.repository.IngredientRepository;
import ru.mail.polis.receptologistbackend.repository.RecipeRepository;

import java.util.Set;
import java.util.TreeSet;

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

    public Set<Recipe> getRecipesWhichCanContainTheseIngredients(String[] ing) {
        Set<Recipe> recipes = new TreeSet<>();
        for (int i = 0; i < ing.length; i++) {
            Ingredient ingredient = ingredientRepository.findByName(firstLetterUpperCase(ing[i]));
            if (ingredient != null) {
                recipes.addAll(ingredient.getRecipes());
            }
        }

        return recipes;
    }

    public Set<Recipe> getRecipesWhichContainAllTheseIngredients(String[] ing) {
        throw new UnsupportedOperationException("Implement me!");
    }

    private String firstLetterUpperCase(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
