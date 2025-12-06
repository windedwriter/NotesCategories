package com.example.notascategory;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> notes;
    private NoteController noteController;
    private Runnable reloadCallback;

    public NotesAdapter(List<Note> notes, NoteController noteController, Runnable reloadCallback) {
        this.notes = notes;
        this.noteController = noteController;
        this.reloadCallback = reloadCallback;
    }

    public void setNotes(List<Note> newNotes) {
        this.notes = newNotes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note n = notes.get(position);

        holder.tvTitle.setText(n.note_title);
        holder.tvContent.setText(n.note_content);

        Date date = new Date(n.created_at);
        String formatted = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                .format(date);
        holder.tvDate.setText(formatted);


        holder.btnDeleteNote.setOnClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Eliminar nota")
                    .setMessage("¿Seguro que quieres eliminar esta nota?")
                    .setPositiveButton("Eliminar", (dialog, which) -> {
                        noteController.deleteNote(n);
                        if (reloadCallback != null) {
                            reloadCallback.run();
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return notes == null ? 0 : notes.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent, tvDate;
        Button btnDeleteNote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvNoteTitle);
            tvContent = itemView.findViewById(R.id.tvNoteContent);
            tvDate = itemView.findViewById(R.id.tvNoteDate);
            btnDeleteNote = itemView.findViewById(R.id.btnDeleteNote);
        }
    }
}
