package com.example.notascategory;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {


    @Query("SELECT * FROM notes WHERE category_id = :categoryId")
    List<Note> getNotesByCategory(int categoryId);


    @Query("SELECT * FROM notes " +
            "WHERE note_title   LIKE '%' || :text || '%' " +
            "   OR note_content LIKE '%' || :text || '%' ")
    List<Note> searchNotes(String text);


    @Insert
    long insertNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Update
    void updateNote(Note note);

    @Insert
    long insertCategory(Category category);

    @Query("SELECT * FROM categories")
    List<Category> getAllCategories();

    @Query("SELECT * FROM notes")
    List<Note> getAllNotes();
    @Delete
    void deleteCategory(Category category);
}
