package ru.mail.polis.receptologistbackend.domain;

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

    private String amount; // amount of this ingredient in recipe

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @ManyToMany
            @JoinTable(
                    name = "ingredient_recipe",
                    joinColumns = @JoinColumn(name = "ingredient_id"),
                    inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    Set<Recipe> recipes; // this set contains recipes where this ingredient used
}
