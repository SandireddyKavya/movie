package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.NotesCommand;
import com.anvesh.recepieapp.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {


    @Override
    public Notes convert(NotesCommand notesCommand) {
        final Notes notes = new Notes();
        notes.setRecipe(notesCommand.getRecipe());
        notes.setRecipeNotes(notesCommand.getRecipeNotes());
        return notes;
    }

}
