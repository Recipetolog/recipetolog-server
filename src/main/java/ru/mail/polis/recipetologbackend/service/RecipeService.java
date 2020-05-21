package ru.mail.polis.recipetologbackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.mail.polis.recipetologbackend.domain.Ingredient;
import ru.mail.polis.recipetologbackend.domain.IngredientRecipe;
import ru.mail.polis.recipetologbackend.domain.Recipe;
import ru.mail.polis.recipetologbackend.repository.IngredientRecipeRepository;
import ru.mail.polis.recipetologbackend.repository.IngredientRepository;
import ru.mail.polis.recipetologbackend.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

@Service
public class RecipeService {
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private IngredientRecipeRepository ingredientRecipeRepository;

    public RecipeService(RecipeRepository recipeRepository,
                         IngredientRepository ingredientRepository,
                         IngredientRecipeRepository ingredientRecipeRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientRecipeRepository = ingredientRecipeRepository;
    }

    public Recipe getRecipeById(long id) {
        return recipeRepository.findById(id);
    }

    public RecipeWrapper getRecipes(int from, int count) {
        return new RecipeWrapper(recipeRepository.findByIdBetween(from, from + count));
    }

    public RecipeWrapper getRecipesWhichCanContainTheseIngredients(String[] ing, int from, int count) {
        Set<Long> idsOfRecipes = new HashSet<>();
        Set<Ingredient> ingredients = new HashSet<>();
        for (String i : ing) {
            Ingredient ingredient = ingredientRepository.findByName(i.toLowerCase());
            if (ingredient != null) {
                idsOfRecipes.addAll(ingredient.getRecipes());
                ingredients.add(ingredient);
            }
        }

        List<Recipe> recipes = new ArrayList<>();
        for (long l : idsOfRecipes) {
            recipes.add(recipeRepository.findById(l));
        }
        // recipe with more matching ingredients will be the first
        recipes.sort((o1, o2) -> {
            Set<Ingredient> ingredients1 = new HashSet<>();
            o1.getIngredients().forEach(ir -> ingredients1.add(ir.getIngredient()));
            ingredients1.retainAll(ingredients);
            Set<Ingredient> ingredients2 = new HashSet<>();
            o2.getIngredients().forEach(ir -> ingredients2.add(ir.getIngredient()));
            ingredients2.retainAll(ingredients);
            return ingredients2.size() - ingredients1.size();
        });

        return getRecipeWrapper(recipes, from, count);
    }

    public RecipeWrapper getRecipesWhichContainAllTheseIngredients(String[] ing, int from, int count) {
        Queue<Ingredient> ingredients = new LinkedList<>();
        for (String i : ing) {
            Ingredient ingredient = ingredientRepository.findByName(i.toLowerCase());
            if (ingredient == null) {
                /*
                 if one ingredient is null then it means that there are no recipes with all these ingredients
                 so we return empty set
                 */
                return new RecipeWrapper(new ArrayList<>());
            }
            ingredients.add(ingredient);
        }

        Set<Long> idsOfRecipes = new HashSet<>();
        // add ids of recipes of the first ingredient and delete this ingredient
        idsOfRecipes.addAll(ingredients.poll().getRecipes());
        for (Ingredient ingredient : ingredients) {
            idsOfRecipes.retainAll(ingredient.getRecipes()); // intersect
        }

        List<Recipe> recipes = new ArrayList<>();
        for (long l : idsOfRecipes) {
            recipes.add(recipeRepository.findById(l));
        }

        return getRecipeWrapper(recipes, from, count);
    }

    private RecipeWrapper getRecipeWrapper(List<Recipe> recipes, int from, int count) {
        if (from + count <= recipes.size()) {
            return new RecipeWrapper(recipes.subList(from, from + count));
        } else if (from < recipes.size()) {
            return new RecipeWrapper(recipes.subList(from, recipes.size()));
        } else {
            return new RecipeWrapper(new ArrayList<>());
        }
    }

    public Recipe newRecipe(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(json);
        String name = jsonNode.get("name").asText().toLowerCase();
        String imageUrl = jsonNode.get("imageUrl").asText();
        String source = jsonNode.get("source").asText();
        String description = jsonNode.get("description").asText();
        List<String> directions = new LinkedList<>();
        jsonNode.get("directions").forEach(n -> directions.add(n.asText()));
        Recipe recipe = recipeRepository.save(new Recipe(name, imageUrl, source, description, directions));

        jsonNode.get("ingredients").fields().forEachRemaining(f -> {
                    String ingredientName = f.getKey().toLowerCase();
                    Ingredient ingredient = ingredientRepository.findByName(ingredientName);
                    if (ingredient == null) {
                        ingredient = ingredientRepository.save(new Ingredient(ingredientName));
                    }
                    ingredientRecipeRepository.save(new IngredientRecipe(
                            ingredient,
                            recipe,
                            f.getValue().asText().toLowerCase())
                    );
                }
        );

        return recipe;
    }
}
