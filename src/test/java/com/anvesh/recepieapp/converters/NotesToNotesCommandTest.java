package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.NotesCommand;
import com.anvesh.recepieapp.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NotesToNotesCommandTest {
    final Long id = 1L;
    final String rnotes = "notes";
    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    void checkNull() {
        NotesCommand notesCommand = converter.convert(null);
        assertNull(notesCommand);
    }

    @Test
    void convert() {
        Notes notes = new Notes();
        notes.setId(id);
        notes.setRecipeNotes(rnotes);
        NotesCommand command = converter.convert(notes);
        assertEquals(id, command.getId());
        assertEquals(rnotes, command.getRecipeNotes());
    }
}