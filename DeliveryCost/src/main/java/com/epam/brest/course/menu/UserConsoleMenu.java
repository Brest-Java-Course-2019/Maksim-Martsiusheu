package com.epam.brest.course.menu;

import com.epam.brest.course.file.builder.Coefficient;
import com.epam.brest.course.file.builder.CoefficientBuilder;
import com.epam.brest.course.file.parser.XmlCoefficientParser;
import com.epam.brest.course.file.parser.XmlParserException;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class UserConsoleMenu implements UserMenu {

    public void showMenu() {
        String filePath = getClass().getClassLoader().getResource("coefficient.xml").getPath();
        XmlCoefficientParser parser = new XmlCoefficientParser();
        try {
            ArrayList<Node> xmlCoefficients = parser.parse(filePath, "pricePerKm");
            CoefficientBuilder builder = new CoefficientBuilder();
            Coefficient coefficient = builder.createCoefficient(xmlCoefficients.get(1));
            System.out.println(coefficient);

        } catch (XmlParserException e) {
            e.printStackTrace();
        }
    }
}
