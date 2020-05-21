package ru.mail.polis.recipetologbackend.service;

import org.springframework.stereotype.Service;
import ru.mail.polis.recipetologbackend.repository.IngredientRepository;

@Service
public class IngredientService {
    IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public IngredientWrapper getAllIngredients() {
        return new IngredientWrapper(ingredientRepository.findAll());
    }
}
