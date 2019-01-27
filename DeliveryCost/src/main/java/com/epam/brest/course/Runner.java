package com.epam.brest.course;

import com.epam.brest.course.file.builder.Coefficient;
import com.epam.brest.course.file.builder.CoefficientBuilder;
import com.epam.brest.course.file.parser.XmlCoefficientParser;
import com.epam.brest.course.file.parser.XmlParserException;
import com.epam.brest.course.menu.UserConsoleMenu;
import org.w3c.dom.NodeList;

public class Runner {
    public static void main(String[] args) {
        UserConsoleMenu menu = new UserConsoleMenu();
        menu.showMenu();
    }

}
