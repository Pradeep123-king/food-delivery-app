package com.tap.dao;

import java.util.List;
import com.tap.model.Restaurant;

public interface RestaurantDAO {

    int addRestaurant(Restaurant restaurant);

    Restaurant getRestaurantById(int restaurantId);

      List<Restaurant> getAllRestaurants();

    int updateRestaurant(Restaurant restaurant);

    int deleteRestaurant(int restaurantId);

	List<Restaurant> searchRestaurants(String keyword);
    
    
}
