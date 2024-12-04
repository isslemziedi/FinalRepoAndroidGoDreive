package com.example.godrivetn.view.components;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.godrivetn.R;
import com.example.godrivetn.model.Reservation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class RevDetailsActivity extends AppCompatActivity {
    private Button confirmButton;
    private ImageView  iv_car_image;
    private TextView tv_car_title ,tv_car_price ,
    full_name , email , phone ,
    datePrise ,dateReturn ,time ,priceTotal ;
    private FirebaseDatabase database;
    private DatabaseReference reservationsRef;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rev_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.revconfirme), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        database = FirebaseDatabase.getInstance();
        reservationsRef = database.getReference("reservations");
        auth = FirebaseAuth.getInstance();

        displayReservationDetails();

        confirmButton = findViewById(R.id.continue3Button);
       confirmButton.setOnClickListener(v -> saveReservation());
    }






    void displayReservationDetails() {
        // Initialisation des vues
        iv_car_image = findViewById(R.id.iv_car_image);
        tv_car_title = findViewById(R.id.tv_car_title);
        tv_car_price = findViewById(R.id.tv_car_price);
        full_name = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.profile);
        dateReturn = findViewById(R.id.dateReturn);
        datePrise = findViewById(R.id.datePrise);
        time = findViewById(R.id.time);
        priceTotal = findViewById(R.id.priceTotal);

        // Récupération des données transmises par l'Intent
        Bundle data = getIntent().getExtras();
        if ( data != null)
        if (data.containsKey("carName") && data.containsKey("pickupDate")) {
            Toast.makeText(this, "Données carName : " + data.getString("carName") + data.getString("pickupDate") + data.getString("driverPhone") , Toast.LENGTH_LONG).show();

        }

       if (data != null) {
            // Récupérer les données et les afficher dans les TextViews
            tv_car_title.setText(data.getString("carName", "Nom indisponible"));
            tv_car_price.setText(String.format(Locale.getDefault(), "%.2f TND", data.getDouble("carPrice", 0.0)));
            full_name.setText(data.getString("driverName", "Nom non spécifié"));
           email.setText(data.getString("driverEmail", "Email non spécifié"));
           //phone.setText(data.getString("driverPhone", "Téléphone non spécifié"));
            datePrise.setText(data.getString("pickupDate", "Date non spécifiée"));
            dateReturn.setText(data.getString("returnDate", "Date non spécifiée"));
            time.setText(data.getString("pickupTime", "Heure non spécifiée"));


           double totalPrice = calculateTotalPrice(data);
            priceTotal.setText(String.format(Locale.getDefault(), "%.2f TND", totalPrice));


            String carImageUrl = data.getString("carImage");
           int resourceId = getResources().getIdentifier(carImageUrl, "drawable", getPackageName());
           if (resourceId != 0) {
               iv_car_image.setImageResource(resourceId); // Image locale
           } else {
               // Sinon, utilisez Glide pour charger une URL distante
               Glide.with(this).load(carImageUrl).into(iv_car_image);
           }
        } else {

            tv_car_title.setText("Détails non disponibles");
        }

    }

   private void saveReservation() {

        Bundle data = getIntent().getExtras();
       // String userId = auth.getCurrentUser().getUid();

        Reservation reservation = new Reservation(
                data.getString("CarName"),
                data.getString("CarImage"),
                data.getDouble("CarPrice"),
                data.getString("driverName"),
                data.getString("driverEmail"),
                data.getString("driverPhone"),
                data.getString("pickupDate"),
                data.getString("returnDate"),
                data.getString("pickupTime"),
                calculateTotalPrice(data)
        );

        String reservationId = reservationsRef.push().getKey();
        reservation.setReservationId(reservationId);

        reservationsRef.child(reservationId).setValue(reservation)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Reservation Success!", Toast.LENGTH_LONG).show();
                    // Envoyer la notification
                    envoyerNotificationReservation();
                    Intent i = new Intent(RevDetailsActivity.this , RevSuccessActivity.class);
                    startActivity(i);

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to save reservation: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                });
    }

    private void envoyerNotificationReservation() {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Créer la notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "reservation_channel")
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("Réservation Confirmée")
                .setContentText("Votre réservation a été effectuée avec succès !")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(1, builder.build());
    }


    private double calculateTotalPrice(Bundle data) {
        // Implement price calculation logic based on dates and car price
        return 0.0;
    }
}
