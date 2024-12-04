package com.example.godrivetn.view.components;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.godrivetn.R;
import com.example.godrivetn.controller.FilterAdapter;
import com.example.godrivetn.model.Brand;
import com.example.godrivetn.model.Car;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilterActivity extends AppCompatActivity {

    private List<Brand> brandList;
    private List<Car> carList = new ArrayList<>();
    private Set<String> selectedFuelTypes = new HashSet<>();
    private Set<String> selectedBrands = new HashSet<>();
    private Set<String> selectedTransmissions = new HashSet<>();


    private SeekBar priceSeekBar;
    private TextView minPriceText, maxPriceText;
    private RecyclerView fuelRecyclerView, brandRecyclerView, transmissionRecyclerView;
    private FilterAdapter fuelAdapter, brandAdapter, transmissionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_filter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.filter_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
        setupRecyclerViews();
        setupPriceFilter();
        setupShowResultButton();

    }

    private void initializeViews() {
        priceSeekBar = findViewById(R.id.priceSeekBar);
        minPriceText = findViewById(R.id.infbprice);
        maxPriceText = findViewById(R.id.supbprice);
        fuelRecyclerView = findViewById(R.id.fuelRecyclerView);
        brandRecyclerView = findViewById(R.id.brandRecyclerView);
        transmissionRecyclerView = findViewById(R.id.transmissionRecyclerView);
    }

    private void setupRecyclerViews() {
        // Setup Fuel Types RecyclerView
        List<String> fuelTypes = new ArrayList<>();
        fuelTypes.add("Gasoline");
        fuelTypes.add("Diesel");
        fuelTypes.add("Biodiesel");

        fuelAdapter = new FilterAdapter(fuelTypes, selectedFuelTypes);
        fuelRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        fuelRecyclerView.setAdapter(fuelAdapter);

        // Setup Brands RecyclerView
     /*   List<String> brands = new ArrayList<>();
        for (Brand brand : brandList) {
            brands.add(brand.getName());
        }

        brandAdapter = new FilterAdapter(brands, selectedBrands);
        brandRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        brandRecyclerView.setAdapter(brandAdapter);*/

        // Setup Transmission RecyclerView
        List<String> transmissions = new ArrayList<>();
        transmissions.add("Automatic");
        transmissions.add("Manual");

        transmissionAdapter = new FilterAdapter(transmissions, selectedTransmissions);
        transmissionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        transmissionRecyclerView.setAdapter(transmissionAdapter);
    }


    private void setupPriceFilter() {
        priceSeekBar.setMax(150);
        priceSeekBar.setProgress(100);

        priceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minPriceText.setText("From " + progress + "DT/day");
                maxPriceText.setText("To " + (progress + 50) + "DT/day");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void setupShowResultButton() {
        Button showResultButton = findViewById(R.id.showresult);
        showResultButton.setOnClickListener(v -> applyFilters());
    }

    private void applyFilters() {
        List<Car> filteredCars = new ArrayList<>();
        int minPrice = priceSeekBar.getProgress();
        int maxPrice = minPrice + 50;

        for (Car car : carList) {
            boolean matchesFuel = selectedFuelTypes.isEmpty() || selectedFuelTypes.contains(car.getFuel());
            boolean matchesBrand = selectedBrands.isEmpty() || selectedBrands.contains(car.getBrand());
            boolean matchesTransmission = selectedTransmissions.isEmpty() ||
                    selectedTransmissions.contains(car.getTransmission());
            boolean matchesPrice = car.getPricePerDay() >= minPrice &&
                    car.getPricePerDay() <= maxPrice;

            if (matchesFuel && matchesBrand && matchesTransmission && matchesPrice) {
                filteredCars.add(car);
            }
        }

        // Return filtered results to previous activity
        Intent intent = new Intent(FilterActivity.this,FilterResultActivity.class);
        intent.putExtra("filteredCars", (ArrayList<Car>) filteredCars);
        startActivity(intent);


        //   setResult(RESULT_OK, intent);
        //  finish();
    }

}