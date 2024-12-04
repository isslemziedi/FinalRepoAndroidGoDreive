package com.example.godrivetn.view.pages;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.godrivetn.R;
import com.example.godrivetn.databinding.ActivityMapsBinding;
import com.example.godrivetn.model.Car;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private List<Car> carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve the list of cars passed from the previous activity
        carList = (List<Car>) getIntent().getSerializableExtra("CAR_LIST");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (carList != null && !carList.isEmpty()) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();

            for (Car car : carList) {
                double latitude = car.getLatitude();
                double longitude = car.getLongitude();

                if (isValidLocation(latitude, longitude)) {
                    LatLng carLocation = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions().position(carLocation).title(car.getName()));
                    builder.include(carLocation);
                } else {
                    Log.w("MapsActivity", "Invalid location for car: " + car.getName());
                }
            }

            LatLngBounds bounds = builder.build();
            int padding = 100; // offset from edges of the map in pixels
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
        } else {
            // If no cars or invalid data, show default location (Nabeul, Tunisia)
            LatLng nabeul = new LatLng(36.4533, 10.7317);
            mMap.addMarker(new MarkerOptions().position(nabeul).title("Nabeul, Tunisia"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nabeul, 10));
        }
    }

    private boolean isValidLocation(double latitude, double longitude) {
        return latitude != 0 && longitude != 0 &&
                latitude >= -90 && latitude <= 90 &&
                longitude >= -180 && longitude <= 180;
    }
}

