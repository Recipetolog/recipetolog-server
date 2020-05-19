package ru.mail.polis.recipetologbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.polis.recipetologbackend.domain.Recipe;
import ru.mail.polis.recipetologbackend.service.RecipeService;
import ru.mail.polis.recipetologbackend.service.RecipeWrapper;

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
    public RecipeWrapper getRecipesWhichCanContainTheseIngredients(@RequestParam String[] ing,
                                                                   @RequestParam int from,
                                                                   @RequestParam int count) {
        return recipeService.getRecipesWhichCanContainTheseIngredients(ing, from, count);
    }

    @GetMapping("/recipes/with-all-these-ingredients")
    @ResponseBody
    public RecipeWrapper getRecipesWhichContainAllTheseIngredients(@RequestParam String[] ing,
                                                                  @RequestParam int from,
                                                                  @RequestParam int count) {
        return recipeService.getRecipesWhichContainAllTheseIngredients(ing, from, count);
    }

    @PostMapping("/recipes")
    public Recipe newRecipe(@RequestBody String json) throws JsonProcessingException {
        return recipeService.newRecipe(json);
    }
}
