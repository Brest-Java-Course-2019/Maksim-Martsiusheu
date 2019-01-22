package com.epam.brest.cources;

import com.epam.brest.cources.menu.UserConsoleMenu;

public class Runner {
    public static void main(String[] args) {
        UserConsoleMenu consoleMenu = new UserConsoleMenu();
        consoleMenu.showMenu();
    }
}
