package com.example.project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddFoodActivity extends AppCompatActivity {

    private EditText foodNameEditText;
    private EditText foodDescriptionEditText;
    private EditText foodPriceEditText;
    private Button addButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        // Initialize the views
        foodNameEditText = findViewById(R.id.food_name_edittext);
        foodDescriptionEditText = findViewById(R.id.food_description_edittext);
        foodPriceEditText = findViewById(R.id.food_price_edittext);
        addButton = findViewById(R.id.add_button);

        // Set a click listener for the add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood();
            }
        });
    }

    private void addFood() {
        // Get the values entered by the user
        String foodName = foodNameEditText.getText().toString().trim();
        String foodDescription = foodDescriptionEditText.getText().toString().trim();
        String foodPrice = foodPriceEditText.getText().toString().trim();

        // Validate the inputs
        if (foodName.isEmpty() || foodDescription.isEmpty() || foodPrice.isEmpty()) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Save the food details to your database or perform the necessary actions

        // Show a success message
        Toast.makeText(this, "Food added successfully", Toast.LENGTH_SHORT).show();

        // Clear the input fields
        foodNameEditText.setText("");
        foodDescriptionEditText.setText("");
        foodPriceEditText.setText("");
    }
}
