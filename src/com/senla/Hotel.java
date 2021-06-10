package com.senla;


import com.senla.ui.menu.Builder;
import com.senla.ui.menu.MenuController;
import com.senla.ui.menu.Navigator;

public class Hotel {

    public static void main(String[] args) {
        Builder builder = new Builder();
        Navigator navigator = new Navigator();
        MenuController menuController = new MenuController(builder, navigator);
        menuController.run();

    }
}
