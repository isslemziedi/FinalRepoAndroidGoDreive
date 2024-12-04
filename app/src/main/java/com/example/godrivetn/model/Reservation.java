package com.example.godrivetn.model;

public class Reservation {
    private String reservationId;
   // private String carId;
   // private String userId;
    private String carName ;
    private  String carImage ;
    private  double pricebyday ;
    private String driverName;
    private String driverEmail;
    private String driverPhone;
    private String pickupDate;
    private String returnDate;
    private String pickupTime;
    private double totalPrice;
    private String status; // "pending", "confirmed", "completed", "cancelled"
    private long timestamp;


    public Reservation() {
    }

    public Reservation(String carName,  String carImage , double price ,String driverName, String driverEmail,
                       String driverPhone, String pickupDate, String returnDate,
                       String pickupTime, double totalPrice) {
         this.carImage =carImage ;
         this.carName = carName ;
         this.pricebyday = price ;
        this.driverName = driverName;
        this.driverEmail = driverEmail;
        this.driverPhone = driverPhone;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.pickupTime = pickupTime;
        this.totalPrice = totalPrice;
        this.status = "pending";
        this.timestamp = System.currentTimeMillis();
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarImage() {
        return carImage;
    }

    public double getPricebyday() {
        return pricebyday;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}