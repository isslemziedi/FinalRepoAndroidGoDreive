package com.example.godrivetn.view.components;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.godrivetn.R;

import java.util.Locale;

public class Rev1Activity extends AppCompatActivity {
    private EditText pickupDateEditText, retourDateEditText, timeEditText;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rev1);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dateinforev1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       // String carId = getIntent().getStringExtra("carId");

        initializeViews();
        setupDatePickers();

        continueButton.setOnClickListener(v -> {
            if (validateInputs()) {
                Intent intent = new Intent(Rev1Activity.this, Rev2Activity.class);
                Bundle previousData = getIntent().getExtras();
                if (previousData != null) {
                    intent.putExtras(previousData);
                }

                intent.putExtra("pickupDate", pickupDateEditText.getText().toString());
                intent.putExtra("returnDate", retourDateEditText.getText().toString());
                intent.putExtra("pickupTime", timeEditText.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void initializeViews() {
        pickupDateEditText = findViewById(R.id.pickupDateEditText);
        retourDateEditText = findViewById(R.id.retourDateEditText);
        timeEditText = findViewById(R.id.TimeEditText);
        continueButton = findViewById(R.id.continue1Button);
    }

    private void setupDatePickers() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, day) -> {
            String date = String.format(Locale.getDefault(), "%02d/%02d/%d", day, month + 1, year);
            if ("pickup".equals(view.getTag())) {
                pickupDateEditText.setText(date);
            } else if ("return".equals(view.getTag())) {
                retourDateEditText.setText(date);
            }
        };

        pickupDateEditText.setOnClickListener(v -> showDatePicker("pickup", dateSetListener));
        retourDateEditText.setOnClickListener(v -> showDatePicker("return", dateSetListener));
        timeEditText.setOnClickListener(v -> showTimePicker());
    }


    private void showDatePicker(String tag, DatePickerDialog.OnDateSetListener dateSetListener) {
        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        int year = calendar.get(java.util.Calendar.YEAR);
        int month = calendar.get(java.util.Calendar.MONTH);
        int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    view.setTag(tag);
                    dateSetListener.onDateSet(view, selectedYear, selectedMonth, selectedDay);
                },
                year,
                month,
                day
        );
        datePickerDialog.show();
    }


    private void showTimePicker() {
        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        int hour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
        int minute = calendar.get(java.util.Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, selectedHour, selectedMinute) -> {
                    String time = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
                    timeEditText.setText(time);
                },
                hour,
                minute,
                true // Format 24 heures
        );
        timePickerDialog.show();
    }

    private boolean validateInputs() {
        if (pickupDateEditText.getText().toString().isEmpty()) {
            pickupDateEditText.setError("Veuillez sélectionner une date de prise en charge");
            return false;
        }
        if (retourDateEditText.getText().toString().isEmpty()) {
            retourDateEditText.setError("Veuillez sélectionner une date de retour");
            return false;
        }
        if (timeEditText.getText().toString().isEmpty()) {
            timeEditText.setError("Veuillez sélectionner une heure de prise en charge");
            return false;
        }
        return true;
    }
}
