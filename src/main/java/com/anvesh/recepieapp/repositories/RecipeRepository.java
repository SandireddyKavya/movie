package com.anvesh.recepieapp.repositories;

import com.anvesh.recepieapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
