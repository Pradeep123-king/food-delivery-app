package com.tap.model;

public class Menu {

    private int menuId;
    private String menuName;
    private double price;
    private boolean isAvailable;
    private String description;
    private String imageUrl;
    private int restaurantId;

  
    public Menu()
    {
    	
    }

    public Menu(int menuId, String menuName, double price, boolean isAvailable,
                String description, String imageUrl, int restaurantId) 
    {
        this.menuId = menuId;
        this.menuName = menuName;
        this.price = price;
        this.isAvailable = isAvailable;
        this.description = description;
        this.imageUrl = imageUrl;
        this.restaurantId = restaurantId;
    }

 
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

	public int getItemName() {
		// TODO Auto-generated method stub
		return 0;
	}
}
