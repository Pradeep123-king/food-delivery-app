package com.tap.dao;
import java.util.List;
import com.tap.model.Menu;

public interface MenuDAO {

    boolean addMenu(Menu menu);

    boolean updateMenu(Menu menu);

    boolean deleteMenu(int menuId);

    Menu getMenuById(int menuId);

    List<Menu> getAllMenus();

    List<Menu> getMenusByRestaurantId(int restaurantId);

	List<Menu> searchMenu(String keyword);
}
