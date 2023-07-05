package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewFoodActivity extends AppCompatActivity {

    private ListView foodListView;
    private ArrayAdapter<String> foodListAdapter;
    private Map<String, FoodItem> foodMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food);

        // Initialize the ListView
        foodListView = findViewById(R.id.food_listview);

        // Retrieve the food items from SharedPreferences and populate the ListView
        retrieveFoodItems();

        // Set click listener for ListView items
        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String foodName = foodListAdapter.getItem(position);
                FoodItem foodItem = foodMap.get(foodName);

                if (foodItem != null) {
                    // Display the details of the selected food item
                    showFoodDetails(foodItem);
                }
            }
        });
    }

    private void retrieveFoodItems() {
        // Retrieve the food items from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("FoodPrefs", MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        List<String> foodItems = new ArrayList<>();
        foodMap = new HashMap<>();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().endsWith("_name")) {
                String foodName = entry.getValue().toString();
                foodItems.add(foodName);

                // Retrieve the food details using the food name as the key
                String foodKey = entry.getKey().replace("_name", "");
                String foodDescription = sharedPreferences.getString(foodKey + "_description", "");
                String foodPrice = sharedPreferences.getString(foodKey + "_price", "");

                // Create a FoodItem object and store it in the foodMap
                FoodItem foodItem = new FoodItem(foodName, foodDescription, foodPrice);
                foodMap.put(foodName, foodItem);
            }
        }

        // Create an ArrayAdapter to populate the food items in the ListView
        foodListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foodItems);
        foodListView.setAdapter(foodListAdapter);
    }

    private void showFoodDetails(FoodItem foodItem) {
        // Implement your logic to display the details of the selected food item
        // You can use a dialog, a new activity, or any other approach based on your requirements
        // Here, we'll show a toast message with the food details

        String details = "Name: " + foodItem.getName() + "\n"
                + "Description: " + foodItem.getDescription() + "\n"
                + "Price: " + foodItem.getPrice();

        Toast.makeText(this, details, Toast.LENGTH_SHORT).show();
    }

    // Define a FoodItem class to hold the details of a food item
    private static class FoodItem {
        private String name;
        private String description;
        private String price;

        public FoodItem(String name, String description, String price) {
            this.name = name;
            this.description = description;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getPrice() {
            return price;
        }
    }
}
