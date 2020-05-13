package ru.mail.polis.receptologistbackend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@Table(name = "recipe")
public class Recipe implements Serializable, Comparable<Recipe> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; // name of recipe

    @ElementCollection
    @CollectionTable(name = "ingredient_recipe", joinColumns = @JoinColumn(name = "recipe_id"))
    @MapKeyJoinColumn(name = "ingredient_id")
    @Column(name = "amount")
    Map<Ingredient, String> ingredients; // this map contains ingredients of this recipe and their amount

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
