package com.tap.model;

public class Restaurant {

    private int restaurantId;
    private String restaurantName;
    private String address;
    private double rating;
    private String cuisine;
    private String deliveryTime;
    private boolean isActive;
    private String imageURL;
    private int userId;

   

    public Restaurant() 
    {
    	
    }

    public Restaurant(String restaurantName, String address, double rating, String cuisine,
                      String deliveryTime, boolean isActive, String imageURL, int userId) {
        this.restaurantName = restaurantName;
        this.address = address;
        this.rating = rating;
        this.cuisine = cuisine;
        this.deliveryTime = deliveryTime;
        this.isActive = isActive;
        this.imageURL = imageURL;
        this.userId = userId;
    }

    // ------------------- Getters & Setters --------------------

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

  

    @Override
    public String toString()
    {
        return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName +
                ", address=" + address + ", rating=" + rating + ", cuisine=" + cuisine +
                ", deliveryTime=" + deliveryTime + ", isActive=" + isActive +
                ", imageURL=" + imageURL + ", userId=" + userId + "]";
    }
}
