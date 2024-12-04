/*
package com.example.godrivetn.model;

import java.io.Serializable;

public class Car implements Serializable {
    private String name;
    private String transmission;
    private String fuel; // Added fuel type
    private String brand; // Added brand
    private String availability; // Added availability
    private int nbPlace; // Added number of places
    private double rating;
    private int pricePerDay;
    private String imageResource;
    private boolean isFavorite;
    private Owner owner; // References the separate Owner class

    // Empty constructor for Firebase
    public Car() {}

    // Constructor with all attributes
    public Car(String name, String transmission, String fuel, String brand, String availability, int nbPlace, double rating, int pricePerDay, String imageResource, boolean isFavorite, Owner owner) {
        this.name = name;
        this.transmission = transmission;
        this.fuel = fuel;
        this.brand = brand;
        this.availability = availability;
        this.nbPlace = nbPlace;
        this.rating = rating;
        this.pricePerDay = pricePerDay;
        this.imageResource = imageResource;
        this.isFavorite = isFavorite;
        this.owner = owner;
    }

    // Getters
    public String getName() { return name; }
    public String getTransmission() { return transmission; }
    public String getFuel() { return fuel; }
    public String getBrand() { return brand; }
    public String getAvailability() { return availability; }
    public int getNbPlace() { return nbPlace; }
    public double getRating() { return rating; }
    public int getPricePerDay() { return pricePerDay; }
    public String getImageResource() { return imageResource; }
    public boolean isFavorite() { return isFavorite; }
    public Owner getOwner() { return owner; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setTransmission(String transmission) { this.transmission = transmission; }
    public void setFuel(String fuel) { this.fuel = fuel; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setAvailability(String availability) { this.availability = availability; }
    public void setNbPlace(int nbPlace) { this.nbPlace = nbPlace; }
    public void setRating(double rating) { this.rating = rating; }
    public void setPricePerDay(int pricePerDay) { this.pricePerDay = pricePerDay; }
    public void setImageResource(String imageResource) { this.imageResource = imageResource; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
    public void setOwner(Owner owner) { this.owner = owner; }
}
*/

package com.example.godrivetn.model;

import java.io.Serializable;

public class Car implements Serializable {
    private String name;
    private String transmission;
    private String fuel; // Added fuel type
    private String brand; // Added brand
    private String availability; // Added availability
    private int nbPlace; // Added number of places
    private double rating;
    private int pricePerDay;
    private String imageResource;
    private boolean isFavorite;
    private Owner owner; // References the separate Owner class

    private double latitude;
    private double longitude;

    // Empty constructor for Firebase
    public Car() {}

    // Constructor with all attributes
    public Car(String name, String transmission, String fuel, String brand, String availability,
               int nbPlace, double rating, int pricePerDay, String imageResource,
               boolean isFavorite, Owner owner, double latitude, double longitude) {
        this.name = name;
        this.transmission = transmission;
        this.fuel = fuel;
        this.brand = brand;
        this.availability = availability;
        this.nbPlace = nbPlace;
        this.rating = rating;
        this.pricePerDay = pricePerDay;
        this.imageResource = imageResource;
        this.isFavorite = isFavorite;
        this.owner = owner;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters
    public String getName() { return name; }
    public String getTransmission() { return transmission; }
    public String getFuel() { return fuel; }
    public String getBrand() { return brand; }
    public String getAvailability() { return availability; }
    public int getNbPlace() { return nbPlace; }
    public double getRating() { return rating; }
    public int getPricePerDay() { return pricePerDay; }
    public String getImageResource() { return imageResource; }
    public boolean isFavorite() { return isFavorite; }
    public Owner getOwner() { return owner; }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setTransmission(String transmission) { this.transmission = transmission; }
    public void setFuel(String fuel) { this.fuel = fuel; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setAvailability(String availability) { this.availability = availability; }
    public void setNbPlace(int nbPlace) { this.nbPlace = nbPlace; }
    public void setRating(double rating) { this.rating = rating; }
    public void setPricePerDay(int pricePerDay) { this.pricePerDay = pricePerDay; }
    public void setImageResource(String imageResource) { this.imageResource = imageResource; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
    public void setOwner(Owner owner) { this.owner = owner; }
}
