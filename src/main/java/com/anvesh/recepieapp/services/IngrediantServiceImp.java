package com.anvesh.recepieapp.services;

import com.anvesh.recepieapp.converters.IngredianteToIngredianteCommand;
import com.anvesh.recepieapp.dataTransfers.IngrediantCommand;
import com.anvesh.recepieapp.domain.Ingrediant;
import com.anvesh.recepieapp.repositories.IngrediantRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class IngrediantServiceImp implements IngrediantService {
    private final IngrediantRepo repository;
    private final IngredianteToIngredianteCommand toIngredianteCommand;

    public IngrediantServiceImp(IngrediantRepo repository, IngredianteToIngredianteCommand toIngredianteCommand) {
        this.repository = repository;
        this.toIngredianteCommand = toIngredianteCommand;
    }

    @Override
    public IngrediantCommand findByIngrediantIdAndRecipeId(Long id, Long recipe_id) {
        Optional<Ingrediant> optional = repository.findByIdAndRecipeId(id, recipe_id);
//        System.out.println("Option is not empty");
        Ingrediant ingrediant = optional.orElseThrow(RuntimeException::new);

        IngrediantCommand command = toIngredianteCommand.convert(ingrediant);
        if (command == null) {
            System.out.println("command is null");
        }

        return command;
    }
}
