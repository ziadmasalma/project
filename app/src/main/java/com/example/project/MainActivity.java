package com.example.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

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
        Button viewFoodButton = findViewById(R.id.view_food_button);
        viewFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewFoodActivity.class);
                startActivity(intent);
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

        // Create and start a new thread
        Thread addFoodThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Get the reference to the SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("FoodPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Generate a unique key for the food item
                String foodKey = "food_" + System.currentTimeMillis();

                // Save the food details using the generated key
                editor.putString(foodKey + "_name", foodName);
                editor.putString(foodKey + "_description", foodDescription);
                editor.putString(foodKey + "_price", foodPrice);

                // Commit the changes
                editor.apply();

                // Perform any additional operations or callbacks here

                // Show a success message on the UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Food added successfully", Toast.LENGTH_SHORT).show();

                        // Clear the input fields
                        foodNameEditText.setText("");
                        foodDescriptionEditText.setText("");
                        foodPriceEditText.setText("");
                    }
                });
            }
        });

        // Start the thread
        addFoodThread.start();
    }




}
