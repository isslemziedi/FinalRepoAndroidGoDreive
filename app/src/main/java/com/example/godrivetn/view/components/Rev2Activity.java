package com.example.godrivetn.view.components;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.godrivetn.R;

public class Rev2Activity extends AppCompatActivity {
    private EditText driverNameEditText, driverEmailEditText, driverPhoneEditText;
    private Button continue2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rev2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reservation2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();


        continue2Button.setOnClickListener(v -> {
            if (validateInputs()) {
                Intent intent = new Intent(Rev2Activity.this, RevDetailsActivity.class);
                intent.putExtras(getIntent().getExtras()); // Pass previous data
                intent.putExtra("driverName", driverNameEditText.getText().toString());
                intent.putExtra("driverEmail", driverEmailEditText.getText().toString());
                intent.putExtra("driverPhone", driverPhoneEditText.getText().toString());
                startActivity(intent);
            }
        });
    }

    private boolean validateInputs() {
        // Add validation logic
        return true;
    }

    void initializeViews()
    {
        continue2Button = findViewById(R.id.continue2Button);
        driverNameEditText = findViewById(R.id.DriverNameEditText);
        driverEmailEditText = findViewById(R.id.DriverEmailEditText);
        driverPhoneEditText = findViewById(R.id.DriverPhoneEditText);
    }

}