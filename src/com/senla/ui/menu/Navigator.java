package com.senla.ui.menu;

import java.util.List;

public class Navigator {

    private Menu currentMenu;

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        System.out.println(currentMenu.getName()); // out name menu
        List<MenuItem> items = currentMenu.getItems();
        for (int i = 0; i < items.size(); i++) { // out all items in menu
            System.out.println("\t" + i + ")" + items.get(i).getTitle());
        }
    }

    public void navigate(Integer index) {
        MenuItem item = currentMenu.getItems().get(index); // item по введенному индексу
        if(item.getAction() != null){
        item.doAction();}
        // вызов action item
        setCurrentMenu(item.getNextMenu()); // меню на которое надо вернуться после экшена
    }
}
