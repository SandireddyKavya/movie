package com.anvesh.recepieapp.converters;

import com.anvesh.recepieapp.dataTransfers.NotesCommand;
import com.anvesh.recepieapp.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NotesCommandToNotesTest {

    final Long id = 1L;
    final String rnotes = "notes";
    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void checkNull() {
        Notes notes = converter.convert(null);
        assertNull(notes);
    }

    @Test
    void convert() {
        NotesCommand command = new NotesCommand();
        command.setId(id);
        command.setRecipeNotes(rnotes);
        Notes notes = converter.convert(command);
        assertEquals(id, notes.getId());
        assertEquals(rnotes, notes.getRecipeNotes());

    }
}