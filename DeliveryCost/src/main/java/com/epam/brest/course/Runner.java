package com.epam.brest.course;

import com.epam.brest.course.menu.UserConsoleMenu;


public class Runner {
    public static void main(String[] args){
        UserConsoleMenu menu = new UserConsoleMenu();
        menu.showMenu();
    }

}
