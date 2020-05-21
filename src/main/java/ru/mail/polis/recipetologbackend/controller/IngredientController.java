package ru.mail.polis.recipetologbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.polis.recipetologbackend.service.IngredientService;
import ru.mail.polis.recipetologbackend.service.IngredientWrapper;

@RestController
public class IngredientController {
    IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredients-all")
    public IngredientWrapper recipeById() {
        return ingredientService.getAllIngredients();
    }
}
