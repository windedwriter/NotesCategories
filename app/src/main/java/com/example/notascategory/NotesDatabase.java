package com.example.notascategory;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class, Category.class}, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {

    private static NotesDatabase INSTANCE;

    public abstract NotesDao notesDao();

    public static synchronized NotesDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            NotesDatabase.class,
                            "notes_database"
                    )
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
