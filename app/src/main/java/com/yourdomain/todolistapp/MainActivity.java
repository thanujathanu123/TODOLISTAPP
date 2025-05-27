package com.yourdomain.todolistapp; // Make sure this matches your package

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast; // For user feedback

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Declare UI elements
    private EditText editTextTodo;
    private Button buttonAddTodo;
    private RecyclerView recyclerViewTodos;

    // Declare Adapter and data list
    private TodoAdapter todoAdapter;
    private List<String> todoList; // This will hold our to-do items

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements by finding them in the layout
        editTextTodo = findViewById(R.id.editTextTodo);
        buttonAddTodo = findViewById(R.id.buttonAddTodo);
        recyclerViewTodos = findViewById(R.id.recyclerViewTodos);

        // Initialize the data list
        todoList = new ArrayList<>();

        // Initialize the Adapter with the (initially empty) data list
        todoAdapter = new TodoAdapter(todoList);

        // Setup RecyclerView:
        // 1. Set a LayoutManager (LinearLayoutManager arranges items in a vertical or horizontal list)
        recyclerViewTodos.setLayoutManager(new LinearLayoutManager(this));
        // 2. Set the Adapter
        recyclerViewTodos.setAdapter(todoAdapter);

        // Set OnClickListener for the Add button
        buttonAddTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTodoItem(); // Call a method to handle adding the item
            }
        });
    }

    private void addTodoItem() {
        String todoText = editTextTodo.getText().toString().trim(); // Get text and remove whitespace

        if (!todoText.isEmpty()) { // Check if the input is not empty
            todoAdapter.addItem(todoText); // Add item using the adapter's method
            editTextTodo.setText("");      // Clear the EditText for the next input
        } else {
            // Optionally, show a message if the user tries to add an empty task
            Toast.makeText(this, "Please enter a task!", Toast.LENGTH_SHORT).show();
        }
    }
}