package com.anvesh.recepieapp.repositories;

import com.anvesh.recepieapp.domain.Categorie;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategorierRepo extends CrudRepository<Categorie, Long> {

    Optional<Categorie> findByDescription(String desc);
}
