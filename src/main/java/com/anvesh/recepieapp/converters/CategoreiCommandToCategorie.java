package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.CategorieCommand;
import com.anvesh.recepieapp.domain.Categorie;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoreiCommandToCategorie implements Converter<CategorieCommand, Categorie> {

    @Nullable
    @Override
    public Categorie convert(CategorieCommand categorieCommand) {
        if (categorieCommand == null) {
            return null;
        }
        final Categorie categorie = new Categorie();
        categorie.setId(categorieCommand.getId());
        categorie.setDescription(categorieCommand.getDescription());
        return categorie;
    }
}
