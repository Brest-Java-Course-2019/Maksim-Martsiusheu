package com.epam.brest.cources;

import com.epam.brest.cources.menu.UserConsoleMenu;
import com.epam.brest.cources.parser.Coefficient;
import com.epam.brest.cources.parser.XmlCoefficientParser;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.io.IOException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DeliveryCost {
    public static void main(String[] args) throws Exception {
        XmlCoefficientParser parser = new XmlCoefficientParser();
        UserConsoleMenu menu = new UserConsoleMenu();
        menu.showMenu();
        //ArrayList<Coefficient> coefficients = parser.getCoefficients("/home/maksim/development/DeliveryCost/src/main/resources/coefficient.xml")
    }


    private static float takeFloatNumber(Scanner scanner, String message) {
        float number = 0;
        boolean isCorrectNumber = false;
        System.out.print(message);
        while (!isCorrectNumber) {
            number = scanner.nextFloat();
            if (number > 0) {
                isCorrectNumber = true;
            } else {
                System.out.println("Invalid input! Try again");
                System.out.print(message);
            }
        }
        return number;
    }
}
