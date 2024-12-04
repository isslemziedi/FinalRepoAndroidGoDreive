/*
package com.example.godrivetn.view.pages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.godrivetn.R;
import com.example.godrivetn.controller.AdapterCar;
import com.example.godrivetn.model.Brand;
import com.example.godrivetn.model.Car;
import com.example.godrivetn.view.components.CarDetailsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BrandListCarsActivity extends AppCompatActivity {
    private static final String TAG = "BrandListCarsActivity";

    private RecyclerView recyclerViewBrandCars;
    private AdapterCar carAdapter;
    private List<Car> carList;
    private TextView brandNameTextView;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_cars);

        // Extensive error checking for intent extras
        Brand selectedBrand = null;
        try {
            selectedBrand = getIntent().getParcelableExtra("SELECTED_BRAND");

            if (selectedBrand == null) {
                throw new NullPointerException("Selected brand is null");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error retrieving brand from intent", e);
            Toast.makeText(this, "Error: Unable to load brand details", Toast.LENGTH_LONG).show();
            finish(); // Close the activity if brand can't be retrieved
            return;
        }

        // Initialize views
        recyclerViewBrandCars = findViewById(R.id.recyclerViewBrandCars);
        brandNameTextView = findViewById(R.id.brandNameTextView);
        backButton = findViewById(R.id.backButton);

        // Set brand name
        brandNameTextView.setText(selectedBrand.getName() + " Cars");

        // Back button functionality
        backButton.setOnClickListener(v -> onBackPressed());

        // Initialize car list and adapter
        carList = new ArrayList<>();
        carAdapter = new AdapterCar(this, carList, car -> {
            Intent intent = new Intent(BrandListCarsActivity.this, CarDetailsActivity.class);
            intent.putExtra("CAR_DETAILS", car);
            startActivity(intent);
        });

        recyclerViewBrandCars.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewBrandCars.setAdapter(carAdapter);

        // Load cars for the selected brand
        loadBrandCarsFromFirebase(selectedBrand.getName());
    }

    private void loadBrandCarsFromFirebase(String brandName) {
        try {
            // Log the exact brand name being searched
            Log.d(TAG, "Searching for cars with brand: '" + brandName + "'");

            DatabaseReference carReference = FirebaseDatabase.getInstance().getReference("cars");

            // Use a more robust query
            carReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    carList.clear();

                    // Log total number of car nodes
                    Log.d(TAG, "Total cars in database: " + snapshot.getChildrenCount());

                    for (DataSnapshot carSnapshot : snapshot.getChildren()) {
                        try {
                            Car car = carSnapshot.getValue(Car.class);

                            // Detailed logging for each car
                            if (car != null) {
                                Log.d(TAG, "Car found: " + car.getName() +
                                        ", Brand: '" + car.getBrand() +
                                        "', Matching: " + brandName.equals(car.getBrand()));

                                // Case-insensitive brand matching
                                if (brandName.equalsIgnoreCase(car.getBrand())) {
                                    carList.add(car);
                                }
                            }
                        } catch (Exception e) {
                            Log.e(TAG, "Error processing individual car", e);
                        }
                    }

                    // Update UI
                    carAdapter.notifyDataSetChanged();

                    // Logging and user feedback
                    if (carList.isEmpty()) {
                        Log.w(TAG, "No cars found for brand: " + brandName);
                        Toast.makeText(BrandListCarsActivity.this,
                                "No cars found for " + brandName,
                                Toast.LENGTH_LONG).show();
                    } else {
                        Log.d(TAG, "Found " + carList.size() + " cars for brand: " + brandName);
                        Toast.makeText(BrandListCarsActivity.this,
                                "Found " + carList.size() + " cars",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e(TAG, "Database error: " + error.getMessage(), error.toException());
                    Toast.makeText(BrandListCarsActivity.this,
                            "Error loading cars: " + error.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error in loadBrandCarsFromFirebase", e);
            Toast.makeText(this, "Unexpected error loading cars", Toast.LENGTH_LONG).show();
        }
    }
}
 */


package com.example.godrivetn.view.pages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.godrivetn.R;
import com.example.godrivetn.controller.AdapterCar;
import com.example.godrivetn.model.Brand;
import com.example.godrivetn.model.Car;
import com.example.godrivetn.view.components.CarDetailsActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BrandListCarsActivity extends AppCompatActivity {
    private static final String TAG = "BrandListCarsActivity";

    private RecyclerView recyclerViewBrandCars;
    private AdapterCar carAdapter;
    private List<Car> carList;
    private TextView brandNameTextView;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_cars);

        // Extensive error checking for intent extras
        Brand selectedBrand = null;
        try {
            selectedBrand = getIntent().getParcelableExtra("SELECTED_BRAND");

            if (selectedBrand == null) {
                throw new NullPointerException("Selected brand is null");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error retrieving brand from intent", e);
            Toast.makeText(this, "Error: Unable to load brand details", Toast.LENGTH_LONG).show();
            finish(); // Close the activity if brand can't be retrieved
            return;
        }

        // Initialize views
        recyclerViewBrandCars = findViewById(R.id.recyclerViewBrandCars);
        brandNameTextView = findViewById(R.id.brandNameTextView);
        backButton = findViewById(R.id.backButton);

        // Set brand name
        brandNameTextView.setText(selectedBrand.getName() + " Cars");

        // Back button functionality
        backButton.setOnClickListener(v -> onBackPressed());

        // Initialize car list and adapter
        carList = new ArrayList<>();
        carAdapter = new AdapterCar(this, carList, car -> {
            Intent intent = new Intent(BrandListCarsActivity.this, CarDetailsActivity.class);
            intent.putExtra("CAR_DETAILS", car);
            startActivity(intent);
        });

        recyclerViewBrandCars.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewBrandCars.setAdapter(carAdapter);

        // Load cars for the selected brand
        loadBrandCarsFromFirebase(selectedBrand.getName());
    }

    private void loadBrandCarsFromFirebase(String brandName) {
        try {
            // Log the exact brand name being searched
            Log.d(TAG, "Searching for cars with brand: '" + brandName + "'");

            DatabaseReference carReference = FirebaseDatabase.getInstance().getReference("cars");

            // Use a more robust query
            carReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    carList.clear();

                    // Log total number of car nodes
                    Log.d(TAG, "Total cars in database: " + snapshot.getChildrenCount());

                    for (DataSnapshot carSnapshot : snapshot.getChildren()) {
                        try {
                            Car car = carSnapshot.getValue(Car.class);

                            // Detailed logging for each car
                            if (car != null) {
                                Log.d(TAG, "Car found: " + car.getName() +
                                        ", Brand: '" + car.getBrand() +
                                        "', Matching: " + brandName.equals(car.getBrand()));

                                // Case-insensitive brand matching
                                if (brandName.equalsIgnoreCase(car.getBrand())) {
                                    carList.add(car);
                                }
                            }
                        } catch (Exception e) {
                            Log.e(TAG, "Error processing individual car", e);
                        }
                    }

                    // Update UI
                    carAdapter.notifyDataSetChanged();

                    // Logging and user feedback
                    if (carList.isEmpty()) {
                        Log.w(TAG, "No cars found for brand: " + brandName);
                        Toast.makeText(BrandListCarsActivity.this,
                                "No cars found for " + brandName,
                                Toast.LENGTH_LONG).show();
                    } else {
                        Log.d(TAG, "Found " + carList.size() + " cars for brand: " + brandName);
                        Toast.makeText(BrandListCarsActivity.this,
                                "Found " + carList.size() + " cars",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e(TAG, "Database error: " + error.getMessage(), error.toException());
                    Toast.makeText(BrandListCarsActivity.this,
                            "Error loading cars: " + error.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error in loadBrandCarsFromFirebase", e);
            Toast.makeText(this, "Unexpected error loading cars", Toast.LENGTH_LONG).show();
        }
    }
}