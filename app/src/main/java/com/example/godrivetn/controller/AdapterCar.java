/*
package com.example.godrivetn.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.godrivetn.R;
import com.example.godrivetn.model.Car;
import com.example.godrivetn.view.components.CarDetailsActivity;



import java.util.List;

public class AdapterCar extends RecyclerView.Adapter<AdapterCar.MyViewHolder> {
    private List<Car> carsList;
    private Context context;
    private OnCarClickListener onCarClickListener;

    public interface OnCarClickListener {
        void onCarClick(Car car);
    }

    public AdapterCar(Context context, List<Car> carsList) {
        this.context = context;
        this.carsList = carsList;
    }

    public AdapterCar(Context context, List<Car> carsList, OnCarClickListener listener) {
        this.context = context;
        this.carsList = carsList;
        this.onCarClickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Car c = carsList.get(position);
        holder.carName.setText(c.getName());

        // Convertir le nom de la ressource en ID de ressource
        int resourceId = context.getResources().getIdentifier(
                c.getImageResource(),
                "drawable",
                context.getPackageName()
        );

        holder.itemView.setOnClickListener(v -> {
            if (onCarClickListener != null) {
                onCarClickListener.onCarClick(c);
            }
        });


        // Définir l'image
        holder.carImage.setImageResource(resourceId);

        holder.rating.setText(String.format("%.1f", c.getRating()));
        holder.price.setText(String.valueOf(c.getPricePerDay()));
        holder.transmission.setText(c.getTransmission());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_home_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return this.carsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView carName, transmission, rating, price;
        public ImageView carImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            carName = itemView.findViewById(R.id.carName);
            carImage = itemView.findViewById(R.id.carImage);
            transmission = itemView.findViewById(R.id.transmission);
            price = itemView.findViewById(R.id.price);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}

*/



package com.example.godrivetn.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.godrivetn.R;
import com.example.godrivetn.model.Car;
import com.example.godrivetn.view.components.CarDetailsActivity;



import java.util.List;

public class AdapterCar extends RecyclerView.Adapter<AdapterCar.MyViewHolder> {
    private List<Car> carsList;
    private Context context;
    private OnCarClickListener onCarClickListener;

    public interface OnCarClickListener {
        void onCarClick(Car car);
    }

    public AdapterCar(Context context, List<Car> carsList) {
        this.context = context;
        this.carsList = carsList;
    }

    public AdapterCar(Context context, List<Car> carsList, OnCarClickListener listener) {
        this.context = context;
        this.carsList = carsList;
        this.onCarClickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Car c = carsList.get(position);
        holder.carName.setText(c.getName());

        // Convertir le nom de la ressource en ID de ressource
        int resourceId = context.getResources().getIdentifier(
                c.getImageResource(),
                "drawable",
                context.getPackageName()
        );

        holder.itemView.setOnClickListener(v -> {
            if (onCarClickListener != null) {
                onCarClickListener.onCarClick(c);
            }
        });


        // Définir l'image
        holder.carImage.setImageResource(resourceId);

        holder.rating.setText(String.format("%.1f", c.getRating()));
        holder.price.setText(String.valueOf(c.getPricePerDay()));
        holder.transmission.setText(c.getTransmission());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_home_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return this.carsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView carName, transmission, rating, price;
        public ImageView carImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            carName = itemView.findViewById(R.id.carName);
            carImage = itemView.findViewById(R.id.carImage);
            transmission = itemView.findViewById(R.id.transmission);
            price = itemView.findViewById(R.id.price);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}
