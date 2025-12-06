package com.example.notascategory;

import android.content.Context;

import java.util.List;

public class NoteController {

    private final NotesDao dao;

    public NoteController(Context context) {
        NotesDatabase db = NotesDatabase.getInstance(context);
        this.dao = db.notesDao();
    }


    public long addNote(String title, String content, int categoryId) {
        Note n = new Note();
        n.note_title = title;
        n.note_content = content;
        n.created_at = System.currentTimeMillis();
        n.category_id = categoryId;
        return dao.insertNote(n);
    }


    public List<Note> getAllNotes() {
        return dao.getAllNotes();
    }


    public List<Note> getNotesByCategory(int categoryId) {
        return dao.getNotesByCategory(categoryId);
    }


    public List<Note> searchNotes(String text) {
        return dao.searchNotes(text);
    }

    public void deleteNote(Note n) {
        dao.deleteNote(n);
    }
}
