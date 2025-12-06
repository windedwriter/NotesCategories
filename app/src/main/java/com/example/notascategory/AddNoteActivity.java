package com.example.notascategory;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etNoteTitle, etNoteContent;
    private Spinner spinnerCategories;
    private Button btnSaveNote;

    private NoteController noteController;
    private CategoryController categoryController;

    private List<Category> categories;
    private List<Integer> categoryIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etNoteTitle = findViewById(R.id.etNoteTitle);
        etNoteContent = findViewById(R.id.etNoteContent);
        spinnerCategories = findViewById(R.id.spinnerCategories);
        btnSaveNote = findViewById(R.id.btnSaveNote);

        noteController = new NoteController(this);
        categoryController = new CategoryController(this);

        loadCategoriesIntoSpinner();

        btnSaveNote.setOnClickListener(v -> {
            String title = etNoteTitle.getText().toString().trim();
            String content = etNoteContent.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(this, "Escribe un título", Toast.LENGTH_SHORT).show();
                return;
            }
            if (content.isEmpty()) {
                Toast.makeText(this, "Escribe contenido", Toast.LENGTH_SHORT).show();
                return;
            }
            if (categories == null || categories.isEmpty()) {
                Toast.makeText(this, "Primero crea una categoría", Toast.LENGTH_SHORT).show();
                return;
            }

            int pos = spinnerCategories.getSelectedItemPosition();
            int categoryId = categoryIds.get(pos);

            noteController.addNote(title, content, categoryId);
            Toast.makeText(this, "Nota guardada", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void loadCategoriesIntoSpinner() {
        categories = categoryController.getAllCategories();

        List<String> names = new ArrayList<>();
        categoryIds.clear();

        for (Category c : categories) {
            names.add(c.category_name);
            categoryIds.add(c.category_id);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                names
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapter);
    }
}
