package ru.mail.polis.receptologistbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.polis.receptologistbackend.domain.Recipe;
import ru.mail.polis.receptologistbackend.service.RecipeService;

import java.util.ArrayList;
import java.util.List;

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
    public List<Recipe> getRecipesWhichContainTheseIngredients(@RequestBody String[] ingredients) {

        return new ArrayList<>(); // stub
    }
}
