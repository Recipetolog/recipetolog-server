package ru.mail.polis.receptologistbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ingredient")
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name; // ingredient name

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public Set<Long> getRecipes() {
        return recipes;
    }

    @ElementCollection
    @CollectionTable(name = "ingredient_recipe", joinColumns = @JoinColumn(name = "ingredient_id"))
    @Column(name = "recipe_id")
    private Set<Long> recipes; // this set contains ids of recipes in which this ingredient used
}
