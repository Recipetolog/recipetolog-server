package ru.mail.polis.recipetologbackend.service;

import ru.mail.polis.recipetologbackend.domain.Recipe;

import java.util.List;

public class RecipeWrapper {
    List<Recipe> recipes;

    public RecipeWrapper(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
