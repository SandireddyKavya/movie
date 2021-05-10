package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.NotesCommand;
import com.anvesh.recepieapp.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


// To Convert Notes entity to NotesCommand Entity
@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Override
    public Notes convert(NotesCommand notesCommand) {
        final Notes notes = new Notes();
        notes.setRecipeNotes(notesCommand.getRecipeNotes());
        return notes;
    }

}
