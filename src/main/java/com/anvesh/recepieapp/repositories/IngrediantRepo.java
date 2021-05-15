package com.anvesh.recepieapp.repositories;

import com.anvesh.recepieapp.domain.Ingrediant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngrediantRepo extends CrudRepository<Ingrediant, Long> {
    //    Ingrediant findByIdAndRecipeId(Long id, Long recipe_id);
    Optional<Ingrediant> findByIdAndRecipeId(Long id, Long recipe_id);
}
