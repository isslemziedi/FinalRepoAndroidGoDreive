package com.example.godrivetn.controller;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.godrivetn.R;

import java.util.List;
import java.util.Set;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterViewHolder> {
    private final List<String> items;
    private final Set<String> selectedItems;

    public FilterAdapter(List<String> items, Set<String> selectedItems) {
        this.items = items;
        this.selectedItems = selectedItems;
    }

    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.filter_item_row, parent, false);
        return new FilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterViewHolder holder, int position) {
        String item = items.get(position);
        holder.checkBox.setText(item);
        holder.checkBox.setChecked(selectedItems.contains(item));

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedItems.add(item);
            } else {
                selectedItems.remove(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class FilterViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        FilterViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.filterCheckbox);
        }
    }
}