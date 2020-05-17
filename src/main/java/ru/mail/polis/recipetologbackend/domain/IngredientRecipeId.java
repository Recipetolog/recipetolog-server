package ru.mail.polis.recipetologbackend.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IngredientRecipeId implements Serializable {
    @Column(name = "ingredient_id")
    private Long ingredientId;
    @Column(name = "recipe_id")
    private Long recipeId;

    public IngredientRecipeId() {

    }

    public IngredientRecipeId(Long ingredientId, Long recipeId) {
        this.ingredientId = ingredientId;
        this.recipeId = recipeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, recipeId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        IngredientRecipeId that = (IngredientRecipeId) obj;
        return Objects.equals(ingredientId, that.ingredientId) && Objects.equals(recipeId, that.recipeId);
    }
}
