package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.CategorieCommand;
import com.anvesh.recepieapp.domain.Categorie;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoreiToCategorieCommand implements Converter<Categorie, CategorieCommand> {
    @Override
    public CategorieCommand convert(Categorie categorie) {
        if (categorie == null) {
            return null;
        }
        CategorieCommand command = new CategorieCommand();
        command.setDescription(categorie.getDescription());
        command.setId(categorie.getId());
        return command;
    }
}
