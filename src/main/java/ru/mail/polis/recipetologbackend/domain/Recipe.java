package ru.mail.polis.recipetologbackend.domain;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Map;

@Entity
@Table(name = "recipe")
public class Recipe implements Serializable, Comparable<Recipe> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // name of recipe

    @ElementCollection
    @CollectionTable(name = "ingredient_recipe", joinColumns = @JoinColumn(name = "recipe_id"))
    @MapKeyJoinColumn(name = "ingredient_id")
    @Column(name = "amount")
    Map<Ingredient, String> ingredients; // this map contains ingredients of this recipe and their amount

    public Recipe() {
    }

    public Recipe(String name, Map<Ingredient, String> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Ingredient, String> getIngredients() {
        return ingredients;
    }

    @Override
    public int compareTo(Recipe o) {
        return id.compareTo(o.id);
    }
}
