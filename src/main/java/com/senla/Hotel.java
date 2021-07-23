package com.senla;

import com.senla.my_spring.configurations.Application;
import com.senla.my_spring.configurations.ApplicationContext;
import com.senla.ui.menu.Builder;
import com.senla.ui.menu.MenuController;
import com.senla.ui.menu.Navigator;

public class Hotel {

    public static void main(String[] args) {
        Builder builder = new Builder();
        Navigator navigator = new Navigator();
        ApplicationContext context = Application.run("com.senla");
        MenuController menuController = context.getObject(MenuController.class);
        menuController.run();
    }
}
