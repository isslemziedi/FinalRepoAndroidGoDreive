package com.example.godrivetn.view.pages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.godrivetn.R;
import com.example.godrivetn.controller.AdapterCar;
import com.example.godrivetn.controller.BrandAdapter;
import com.example.godrivetn.model.Brand;
import com.example.godrivetn.model.Car;
import com.example.godrivetn.view.auth.LoginActivity;
import com.example.godrivetn.view.components.CarDetailsActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.example.godrivetn.view.components.FilterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;




public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private AdapterCar carAdapter;
    private List<Car> carList;
    private RecyclerView recyclerViewBrand;
    private BrandAdapter brandAdapter;
    private List<Brand> brandList;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceCar;
    private DatabaseReference databaseReferenceBrand;

    private View GoToHome;
    private  View GoToProfile;
    private View GoToReservation;
    private ImageButton filterbtn;

    private ImageView logOutBtn;
    private TextView userLoc;

    private FirebaseAuth auth;
    private GoogleSignInClient mGoogleSignInClient;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Firebase Authentication and Google Sign-In
        auth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        initializeDatabaseReferences();
        initializeViews();
        setupUserInfo();
        setupNavigation();
        setupRecyclerViews();


        //databaseReference = FirebaseDatabase.getInstance().getReference();
        //databaseReferenceBrand = databaseReference.child("brands");
        //databaseReferenceCar = databaseReference.child("cars");


        /*
        recyclerViewBrand= findViewById(R.id.brandRecyclerView);
        recyclerView1= findViewById(R.id.recycleNearCar);
        recyclerView2 = findViewById(R.id.recyclePopularCar);
        recyclerViewBrand.setNestedScrollingEnabled(false);
        recyclerView1.setNestedScrollingEnabled(false);
        recyclerView2.setNestedScrollingEnabled(false);


        GoToHome = findViewById(R.id.nav_home);
        GoToProfile =  findViewById(R.id.nav_profile);
        GoToReservation = findViewById(R.id.nav_reservation);

        */

        filterbtn = findViewById(R.id.filterbtn);

        filterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this , FilterActivity.class);
                startActivity(intent);
            }
        });
        /*
        GoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        GoToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        GoToReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RevListActivity.class);
                startActivity(intent);
            }
        });


        brandList =new ArrayList<>();


        recyclerViewBrand.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));



        // initializeBrands();
        brandAdapter = new BrandAdapter(brandList);
        recyclerViewBrand.setAdapter(brandAdapter);


        // Charger les données depuis Firebase
        loadBrandsFromFirebase();
        // Charger les voitures depuis Firebase
        loadCarsFromFirebase();



        // Initialize car list
        carList = new ArrayList<>();

        carAdapter = new AdapterCar(this, carList, new AdapterCar.OnCarClickListener() {
            @Override
            public void onCarClick(Car car) {
                // Create an intent to navigate to CarDetailsActivity
                Intent intent = new Intent(HomeActivity.this, CarDetailsActivity.class);

                // Pass the car details to the CarDetailsActivity
                intent.putExtra("CAR_DETAILS", car);

                // Start the CarDetailsActivity
                startActivity(intent);
            }
        });

        recyclerView1.setAdapter(carAdapter);
        recyclerView2.setAdapter(carAdapter);

        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
*/


    }


    private void initializeDatabaseReferences() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReferenceBrand = databaseReference.child("brands");
        databaseReferenceCar = databaseReference.child("cars");
    }

    private void initializeViews() {
        recyclerViewBrand = findViewById(R.id.brandRecyclerView);
        recyclerView1 = findViewById(R.id.recycleNearCar);
        recyclerView2 = findViewById(R.id.recyclePopularCar);
        userLoc = findViewById(R.id.userLocation);

        GoToHome = findViewById(R.id.nav_home);
        GoToProfile = findViewById(R.id.nav_profile);
        GoToReservation = findViewById(R.id.nav_reservation);
        logOutBtn = findViewById(R.id.logOut);

        recyclerViewBrand.setNestedScrollingEnabled(false);
        recyclerView1.setNestedScrollingEnabled(false);
        recyclerView2.setNestedScrollingEnabled(false);
    }

    private void setupUserInfo() {
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            String displayName = currentUser.getDisplayName();
            String email = currentUser.getEmail();
            userLoc.setText(displayName != null ? displayName : email);
        } else {
            // If no user is signed in, redirect to login
            navigateToLogin();
        }
    }

    private void setupNavigation() {
        logOutBtn.setOnClickListener(v -> signOut());

        GoToHome.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        GoToProfile.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        GoToReservation.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, RevListActivity.class);
            startActivity(intent);
        });
    }

    private void setupRecyclerViews() {
        brandList = new ArrayList<>();
        recyclerViewBrand.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        brandAdapter = new BrandAdapter(brandList);
        recyclerViewBrand.setAdapter(brandAdapter);

        // Initialize car list
        carList = new ArrayList<>();
        carAdapter = new AdapterCar(this, carList, car -> {
            Intent intent = new Intent(HomeActivity.this, CarDetailsActivity.class);
            intent.putExtra("CAR_DETAILS", car);
            startActivity(intent);
        });

        recyclerView1.setAdapter(carAdapter);
        recyclerView2.setAdapter(carAdapter);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Load data from Firebase
        loadBrandsFromFirebase();
        // Load cars from Firebase
        loadCarsFromFirebase();
    }



    private void loadBrandsFromFirebase() {
        databaseReferenceBrand.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                brandList.clear();
                for (DataSnapshot brandSnapshot : snapshot.getChildren()) {
                    Brand brand = brandSnapshot.getValue(Brand.class);
                    if (brand != null) {
                        brandList.add(brand);
                    }
                }
                brandAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, "Erreur de chargement: " + error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadCarsFromFirebase() {
        databaseReferenceCar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                carList.clear();
                for (DataSnapshot carSnapshot : snapshot.getChildren()) {
                    Car car = carSnapshot.getValue(Car.class);
                    if (car != null) {
                        carList.add(car);
                    }
                }
                carAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, "Erreur de chargement: " +
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signOut() {
        // Sign out from Firebase Authentication
        auth.signOut();

        // Sign out from Google Sign-In
        mGoogleSignInClient.signOut().addOnCompleteListener(this, task -> {
            // Redirect to login screen
            navigateToLogin();
        });
    }

    private void navigateToLogin() {
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            navigateToLogin();
        }
    }
}