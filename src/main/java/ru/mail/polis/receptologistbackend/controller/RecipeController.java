package ru.mail.polis.receptologistbackend.controller;

import org.springframework.web.bind.annotation.*;
import ru.mail.polis.receptologistbackend.domain.Recipe;
import ru.mail.polis.receptologistbackend.service.RecipeService;

import java.util.Set;

@RestController
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public Recipe recipeById(@RequestParam long id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping("/recipes/with-these-ingredients")
    @ResponseBody
    public Set<Recipe> getRecipesWhichCanContainTheseIngredients(@RequestParam String[] ing) {
        return recipeService.getRecipesWhichCanContainTheseIngredients(ing);
    }

    @GetMapping("/recipes/with-all-these-ingredients")
    @ResponseBody
    public Set<Recipe> getRecipesWhichContainAllTheseIngredients(@RequestParam String[] ing) {
        return recipeService.getRecipesWhichContainAllTheseIngredients(ing);
    }
}
