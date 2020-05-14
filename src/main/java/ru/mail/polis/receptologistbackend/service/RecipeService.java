package ru.mail.polis.receptologistbackend.service;

import org.springframework.stereotype.Service;
import ru.mail.polis.receptologistbackend.domain.Ingredient;
import ru.mail.polis.receptologistbackend.domain.Recipe;
import ru.mail.polis.receptologistbackend.repository.IngredientRepository;
import ru.mail.polis.receptologistbackend.repository.RecipeRepository;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
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
        Queue<Ingredient> ingredients = new LinkedList<>();
        for (String i : ing) {
            Ingredient ingredient = ingredientRepository.findByName(i.toLowerCase());
            if (ingredient == null) {
                /*
                 if one ingredient is null then it means that there are no recipes with all these ingredients
                 so we return empty set
                 */
                return new TreeSet<>();
            }
            ingredients.add(ingredient);
        }

        Set<Long> idsOfRecipes = new HashSet<>();
        // add ids of recipes of the first ingredient and delete this ingredient
        idsOfRecipes.addAll(ingredients.poll().getRecipes());
        for (Ingredient ingredient : ingredients) {
            idsOfRecipes.retainAll(ingredient.getRecipes()); // intersect
        }

        Set<Recipe> recipes = new TreeSet<>();
        for (long l : idsOfRecipes) {
            recipes.add(recipeRepository.findById(l));
        }

        return recipes;
    }
}
