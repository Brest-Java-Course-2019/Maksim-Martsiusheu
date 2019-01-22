package com.epam.brest.cources.menu;

import com.epam.brest.cources.calc.CalculatorImpl;
import com.epam.brest.cources.calc.DataItem;
import com.epam.brest.cources.parser.Coefficient;
import com.epam.brest.cources.parser.XmlCoefficientParser;
import com.epam.brest.cources.parser.XmlParserException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserConsoleMenu {
    final static String FILE_NAME = "coefficient.xml";
    final static String TAGS_NAME = "coefficient";

    final static String HEADER = "+--------------DeliveryCost--------------+";
    final static String MESSAGE_TO_USER = "Pleas, enter ";
    final static String ERROR_INPUT = "Entered number should be more than 0! Try again";
    final static String RESULT = "Delivery cost is ";

    public void showMenu() {
        try {
            Scanner scanner = new Scanner(System.in);

            String filePath = getClass().getClassLoader().getResource(FILE_NAME).getPath();
            XmlCoefficientParser parser = new XmlCoefficientParser();
            ArrayList<Coefficient> coefficients = parser.getCoefficients(filePath, TAGS_NAME);

            System.out.println(HEADER);

            BigDecimal weight = takeCorrectValue(scanner,MESSAGE_TO_USER + "weight: ");
            BigDecimal distance = takeCorrectValue(scanner,MESSAGE_TO_USER + "distance: ");

            DataItem data = new DataItem();
            data.setWeight(weight);
            data.setDistance(distance);

            int i = 0;
            boolean isCorrectCoeff = false;

            while ((i < coefficients.size()) && (!isCorrectCoeff)) {
                if (isCoeffForWeight(weight, coefficients.get(i))) {
                    data.setPricePerKg(coefficients.get(i).getValue());
                    isCorrectCoeff = true;
                } else {
                    i++;
                }
            }

            CalculatorImpl calc = new CalculatorImpl();
            BigDecimal cost = calc.calc(data);

            System.out.println(RESULT + cost);

        } catch (XmlParserException e) {
            e.printStackTrace();
        } catch (InputMismatchException e){
            e.printStackTrace();
        }


    }

    private boolean isCoeffForWeight(BigDecimal weight, Coefficient coeff) {
        return (weight.compareTo(coeff.getLowBorder()) >= 0) && ((weight.compareTo(coeff.getUpperBorder()) < 0));
    }

    private BigDecimal takeCorrectValue(Scanner scanner, String message) {
        //TODO: write right method
        System.out.println(message);

        boolean isCorrect = false;
        BigDecimal inputValue = null;

        while (!isCorrect) {
            String value = scanner.nextLine();
            if (Double.valueOf(value) > 0) {
                inputValue = new BigDecimal(value);
                isCorrect = true;
            } else {
                System.out.println(ERROR_INPUT);
            }
        }
        return inputValue;
    }


}
