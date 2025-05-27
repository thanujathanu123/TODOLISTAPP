package com.yourdomain.todolistapp; // Make sure this matches your package

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.ArrayList; // Import if you initialize with new ArrayList<>()

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<String> todoItems;

    // Constructor
    public TodoAdapter(List<String> todoItems) {
        // It's good practice to initialize if null to prevent NullPointerExceptions later
        if (todoItems == null) {
            this.todoItems = new ArrayList<>();
        } else {
            this.todoItems = todoItems;
        }
    }

    // ViewHolder class: Caches views for a single item
    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTodoItem;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            // Find the TextView within the list_item_todo.xml layout
            textViewTodoItem = itemView.findViewById(R.id.textViewTodoItem);
        }
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view by inflating the item_layout.xml
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_todo, parent, false);
        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        // Get the data model (String) based on position
        String currentItem = todoItems.get(position);

        // Set the text of the TextView in the ViewHolder
        holder.textViewTodoItem.setText(currentItem);
    }

    @Override
    public int getItemCount() {
        // Return the size of your dataset (invoked by the layout manager)
        return todoItems.size();
    }

    // Method to add an item to the list and notify the adapter
    public void addItem(String item) {
        if (item != null && !item.trim().isEmpty()) {
            todoItems.add(item);
            // Notify the adapter that an item was inserted at the last position
            // This is more efficient than notifyDataSetChanged()
            notifyItemInserted(todoItems.size() - 1);
        }
    }
}