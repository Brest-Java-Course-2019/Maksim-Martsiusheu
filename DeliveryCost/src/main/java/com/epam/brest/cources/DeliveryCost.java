package com.epam.brest.cources;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.io.IOException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeliveryCost {
    private static final int MAX_WEIGHT_LITTLE_PROD = 5;
    private static final int MIN_WEIGHT_LARGE_PROD = 10;

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Scanner scanner = new Scanner(System.in);

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("src/main/resources/settings.xml");

            float maxCoeff = Float.valueOf(doc.getElementsByTagName("maxCoefficient").item(0).getTextContent());
            float minCoeff = Float.valueOf(doc.getElementsByTagName("minCoefficient").item(0).getTextContent());
            float averageCoeff = Float.valueOf(doc.getElementsByTagName("averageCoefficient").item(0).getTextContent());

            float weight;
            float distance;

            System.out.print("Weight: ");
            weight = scanner.nextFloat();

            System.out.print("Distance: ");
            distance = scanner.nextFloat();

            float deliveryCost = weight * distance;

            if (weight < MAX_WEIGHT_LITTLE_PROD) {
                deliveryCost *= minCoeff;
            } else if ((weight >= MIN_WEIGHT_LARGE_PROD) && (weight < MAX_WEIGHT_LITTLE_PROD)) {
                deliveryCost *= averageCoeff;
            } else {
                deliveryCost *= maxCoeff;
            }

            System.out.println("Delivery cost: " + deliveryCost + "$");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InputMismatchException e){
            e.printStackTrace();
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
