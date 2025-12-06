package com.example.notascategory;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCategoryActivity extends AppCompatActivity {

    private EditText etCategoryName;
    private Button btnSaveCategory;
    private CategoryController categoryController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        etCategoryName = findViewById(R.id.etCategoryName);
        btnSaveCategory = findViewById(R.id.btnSaveCategory);
        categoryController = new CategoryController(this);

        btnSaveCategory.setOnClickListener(v -> {
            String name = etCategoryName.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Escribe un nombre", Toast.LENGTH_SHORT).show();
                return;
            }

            categoryController.addCategory(name);
            Toast.makeText(this, "Categoría guardada", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
