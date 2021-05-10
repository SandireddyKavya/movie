package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.NotesCommand;
import com.anvesh.recepieapp.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
    @Override
    public NotesCommand convert(Notes notes) {
        NotesCommand command = new NotesCommand();
        command.setRecipeNotes(notes.getRecipeNotes());
        command.setRecipeNotes(notes.getRecipeNotes());
        command.setId(notes.getId());
        return command;
    }
}
