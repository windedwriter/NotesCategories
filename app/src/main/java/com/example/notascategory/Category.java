package com.example.notascategory;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "categories")
public class Category {
    @PrimaryKey(autoGenerate = true)
    int category_id;

    @ColumnInfo(name = "category_name")
    String category_name;
}



