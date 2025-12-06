package com.example.notascategory;

import android.content.Context;

import java.util.List;

public class CategoryController {

    private final NotesDao dao;

    public CategoryController(Context context) {
        NotesDatabase db = NotesDatabase.getInstance(context);
        this.dao = db.notesDao();
    }


    public long addCategory(String name) {
        Category c = new Category();
        c.category_name = name;
        return dao.insertCategory(c);
    }


    public List<Category> getAllCategories() {
        return dao.getAllCategories();
    }
    public void deleteCategory(Category category) {
        dao.deleteCategory(category);
    }
}
