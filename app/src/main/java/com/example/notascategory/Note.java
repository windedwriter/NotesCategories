package com.example.notascategory;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (tableName = "notes",
        foreignKeys = @ForeignKey(
                entity = Category.class,
                parentColumns = "category_id",
                childColumns = "category_id",
                onDelete = ForeignKey.CASCADE ),
        indices = {@Index("category_id")}
)
public class Note {
    @PrimaryKey(autoGenerate = true)
    int note_id;

    @ColumnInfo(name = "note_title")
    String note_title;

    @ColumnInfo(name = "note_content")
    String note_content;

    @ColumnInfo(name = "created_at")
    long created_at;


    @ColumnInfo(name = "category_id")
    int category_id;
}

