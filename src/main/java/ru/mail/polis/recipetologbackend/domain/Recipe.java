package ru.mail.polis.recipetologbackend.domain;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe implements Serializable, Comparable<Recipe> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // name of recipe

    private String imageUrl; // url to image of this recipe

    private String description;

    @OneToMany(
            mappedBy = "recipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<IngredientRecipe> ingredients; // this set contains ingredients of this recipe and their amount

    @ElementCollection
    private List<String> directions; // step by step instructions for this recipe

    public Recipe() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public Set<IngredientRecipe> getIngredients() {
        return ingredients;
    }

    public List<String> getDirections() {
        return directions;
    }

    @Override
    public int compareTo(Recipe o) {
        return id.compareTo(o.id);
    }
}
