package ru.mail.polis.receptologistbackend.service;

import org.springframework.stereotype.Service;
import ru.mail.polis.receptologistbackend.domain.Ingredient;
import ru.mail.polis.receptologistbackend.domain.Recipe;
import ru.mail.polis.receptologistbackend.repository.IngredientRepository;
import ru.mail.polis.receptologistbackend.repository.RecipeRepository;

import java.util.HashSet;
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
        Set<Long> idsOfRecipes = new HashSet<>();
        for (String i : ing) {
            Ingredient ingredient = ingredientRepository.findByName(i.toLowerCase());
            if (ingredient != null) {
                idsOfRecipes.addAll(ingredient.getRecipes());
            }
        }

        Set<Recipe> recipes = new TreeSet<>();
        for (long l : idsOfRecipes) {
            recipes.add(recipeRepository.findById(l));
        }

        return recipes;
    }

    public Set<Recipe> getRecipesWhichContainAllTheseIngredients(String[] ing) {
        throw new UnsupportedOperationException("Implement me!");
    }
}
