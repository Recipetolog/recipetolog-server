package ru.mail.polis.receptologistbackend.domain;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ingredient")
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name; // ingredient name

    @ElementCollection
    @CollectionTable(name = "ingredient_recipe", joinColumns = @JoinColumn(name = "ingredient_id"))
    @Column(name = "recipe_id")
    private Set<Long> recipes; // this set contains ids of recipes in which this ingredient used

    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Long> getRecipes() {
        return recipes;
    }

    @Override
    public String toString() {
        return name;
    }
}
