package com.example.notascategory;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);
    }

    public interface OnDeleteCategoryListener {
        void onDeleteCategory(Category category);
    }

    private List<Category> categories;
    private OnCategoryClickListener clickListener;
    private OnDeleteCategoryListener deleteListener;

    public CategoryAdapter(List<Category> categories,
                           OnCategoryClickListener clickListener,
                           OnDeleteCategoryListener deleteListener) {

        this.categories = categories;
        this.clickListener = clickListener;
        this.deleteListener = deleteListener;
    }

    public void setCategories(List<Category> newCategories) {
        this.categories = newCategories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category c = categories.get(position);
        holder.tvName.setText(c.category_name);


        holder.card.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onCategoryClick(c);
            }
        });


        holder.btnDeleteCategory.setOnClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Eliminar categoría")
                    .setMessage("¿Seguro que deseas eliminar esta categoría y todas sus notas?")
                    .setPositiveButton("Eliminar", (dialog, which) -> {
                        if (deleteListener != null) {
                            deleteListener.onDeleteCategory(c);
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        Button btnDeleteCategory;
        CardView card;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvCategoryName);
            btnDeleteCategory = itemView.findViewById(R.id.btnDeleteCategory);
            card = (CardView) itemView;
        }
    }
}
