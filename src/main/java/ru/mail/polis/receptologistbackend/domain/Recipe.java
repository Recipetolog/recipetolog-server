package ru.mail.polis.receptologistbackend.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; // name of recipe

    @ManyToMany(mappedBy = "recipes")
    Set<Ingredient> ingredients; // this set contains ingredients of this recipe

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }
}
