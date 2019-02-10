package com.epam.brest.course.menu;

import com.epam.brest.course.calculator.Calculator;

import com.epam.brest.course.calculator.DataItem;

import com.epam.brest.course.file.Coefficient;
import com.epam.brest.course.file.XmlParser;
import com.epam.brest.course.file.XmlParserException;

import com.epam.brest.course.selector.CoefficientSelector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class UserConsoleMenu {
    final static int LINE_SIZE = 50;

    final static String HEADER_MESSAGE = "DeliveryCost";
    final static String LINE_SYMBOL = "-";
    final static String CORNER_SYMBOL = "+";

    final static String INCORRECT_INPUT_MESSAGE = "Entered number should be more than 0! Try again";
    final static String INCORRECT_INPUT_VALUE = "It's not a number!";

    final static String FILE_NAME = "coefficient.xml";
    final static String PRICE_PER_KG = "pricePerKg";
    final static String PRICE_PER_KM = "pricePerKm";

    final static Logger LOGGER = LogManager.getLogger();

    public void showMenu() {

        ApplicationContext context = new ClassPathXmlApplicationContext("app.xml", "messages.xml");

        XmlParser parser = (XmlParser) context.getBean("parser");
        Calculator calculator = (Calculator) context.getBean("calc");
        CoefficientSelector selector = (CoefficientSelector) context.getBean("selector");

        Properties messages = (Properties) context.getBean("message");

        Scanner scanner = (Scanner) context.getBean("scanner");

        showLine(HEADER_MESSAGE);

        BigDecimal weight;
        BigDecimal distance;
        BigDecimal totalCost;


        try {
            LOGGER.debug("Price calculation start");

            ArrayList<Coefficient> coefficientsForKg = parser.parse(FILE_NAME, PRICE_PER_KG);
            ArrayList<Coefficient> coefficientsForKm = parser.parse(FILE_NAME, PRICE_PER_KM);

            weight = takeCorrectValue(scanner, messages.getProperty("weight.messageToEnter"));
            distance = takeCorrectValue(scanner, messages.getProperty("distance.messageToEnter"));

            DataItem dataItem = new DataItem();
            dataItem.setWeight(weight);
            dataItem.setDistance(distance);
            dataItem.setPricePerKg(selector.selectCoefficient(coefficientsForKg, weight).getValue());
            dataItem.setPricePerKm(selector.selectCoefficient(coefficientsForKm, distance).getValue());

            totalCost = calculator.calculateCost(dataItem);
            showLine(messages.getProperty("result.message") + totalCost);

            LOGGER.debug("Price calculation end");


        } catch (XmlParserException e) {
            LOGGER.error("Catch XmlParserException");
        }

    }

    private void showLine(String message) {
        int i = 2 * CORNER_SYMBOL.length();
        int middlePosition = (LINE_SIZE - message.length()) / 2;
        System.out.print(CORNER_SYMBOL);
        while (i < LINE_SIZE) {
            if (i == middlePosition) {
                System.out.print(message);
                i += message.length();
            } else {
                System.out.print(LINE_SYMBOL);
                i++;
            }
        }
        System.out.println(CORNER_SYMBOL);
    }

    private BigDecimal takeCorrectValue(Scanner scanner, String message) {
        LOGGER.debug("Console input start");

        System.out.println(message);
        boolean isCorrectValue = false;
        BigDecimal value = null;
        while (!isCorrectValue) {
            try {
                value = new BigDecimal(scanner.nextLine());
                if (value.signum() > 0) {
                    LOGGER.debug("Successful input");
                    isCorrectValue = true;
                } else {
                    LOGGER.debug("Incorrect value");
                    System.out.println(INCORRECT_INPUT_MESSAGE);
                }
            } catch (NumberFormatException e) {
                LOGGER.debug("Incorrect number format!", e);
                System.out.println(INCORRECT_INPUT_VALUE);
            }
        }
        return value;
    }

}