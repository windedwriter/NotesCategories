package com.example.notascategory;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCategories;
    private FloatingActionButton fabAddCategory;

    private CategoryController categoryController;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryController = new CategoryController(this);

        rvCategories = findViewById(R.id.rvCategories);
        fabAddCategory = findViewById(R.id.fabAddCategory);

        rvCategories.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );

        categoryAdapter = new CategoryAdapter(new ArrayList<>(), category -> {
            Intent i = new Intent(MainActivity.this, CategoryNotesActivity.class);
            i.putExtra("category_id", category.category_id);
            i.putExtra("category_name", category.category_name);
            startActivity(i);
            }, category -> {
            categoryController.deleteCategory(category);
            loadCategories();
        });

        rvCategories.setAdapter(categoryAdapter);

        fabAddCategory.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddCategoryActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCategories();
    }

    private void loadCategories() {
        categoryAdapter.setCategories(
                categoryController.getAllCategories()
        );
    }
}