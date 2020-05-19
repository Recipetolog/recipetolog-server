package ru.mail.polis.recipetologbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "ingredient_recipe")
public class IngredientRecipe {
    @EmbeddedId
    @JsonIgnore
    private IngredientRecipeId id;

    @ManyToOne
    @MapsId("ingredientId")
    private Ingredient ingredient;

    @ManyToOne
    @MapsId("recipeId")
    @JsonIgnore
    private Recipe recipe;

    private String amount;

    public IngredientRecipe() {

    }

    public IngredientRecipe(Ingredient ingredient, Recipe recipe, String amount) {
        this.id = new IngredientRecipeId(ingredient.getId(), recipe.getId());
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.amount = amount;
    }

    public IngredientRecipeId getId() {
        return id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public String getAmount() {
        return amount;
    }
}
