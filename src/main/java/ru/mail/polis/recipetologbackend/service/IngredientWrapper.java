package ru.mail.polis.recipetologbackend.service;

import ru.mail.polis.recipetologbackend.domain.Ingredient;

import java.util.List;

public class IngredientWrapper {
    List<Ingredient> ingredients;

    public IngredientWrapper(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
