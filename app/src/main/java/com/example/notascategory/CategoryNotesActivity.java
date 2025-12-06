package com.example.notascategory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CategoryNotesActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY_ID = "category_id";
    public static final String EXTRA_CATEGORY_NAME = "category_name";

    private int categoryId;
    private String categoryName;

    private TextView tvCategoryTitle;
    private RecyclerView rvCategoryNotes;
    private FloatingActionButton fabAddNoteInCategory;

    private NoteController noteController;
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_notes);


        categoryId = getIntent().getIntExtra(EXTRA_CATEGORY_ID, -1);
        categoryName = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);

        if (categoryId == -1) {

            finish();
            return;
        }


        noteController = new NoteController(this);

        tvCategoryTitle = findViewById(R.id.tvCategoryTitle);
        rvCategoryNotes = findViewById(R.id.rvCategoryNotes);
        fabAddNoteInCategory = findViewById(R.id.fabAddNoteInCategory);


        tvCategoryTitle.setText("Notas de: " + categoryName);

        rvCategoryNotes.setLayoutManager(new LinearLayoutManager(this));
        notesAdapter = new NotesAdapter(new ArrayList<>(), noteController, this::loadNotesForCategory);
        rvCategoryNotes.setAdapter(notesAdapter);


        fabAddNoteInCategory.setOnClickListener(v -> {
            Intent i = new Intent(CategoryNotesActivity.this, AddNoteActivity.class);

            i.putExtra(EXTRA_CATEGORY_ID, categoryId);
            startActivity(i);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotesForCategory();
    }

    private void loadNotesForCategory() {
        notesAdapter.setNotes(
                noteController.getNotesByCategory(categoryId)
        );
    }
}
