package com.anvesh.recepieapp.repositories;

import com.anvesh.recepieapp.domain.Categorie;
import org.springframework.data.repository.CrudRepository;

public interface CategorierRepo extends CrudRepository<Categorie, Long> {
}
