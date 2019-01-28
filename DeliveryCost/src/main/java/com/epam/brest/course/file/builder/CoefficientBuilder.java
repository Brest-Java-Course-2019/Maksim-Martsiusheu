package com.epam.brest.course.file.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CoefficientBuilder {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String INFINITY = "infinity";
    private final static String INFINITY_VALUE = "10e30";

    public ArrayList<Coefficient> createCoefficient(ArrayList<Node> nodeList) throws NumberFormatException {
        LOGGER.debug("Building coefficients start");

        ArrayList<Coefficient> coefficientList = new ArrayList<>();
        for(int i = 0; i<nodeList.size();i++) {
            Coefficient coefficient = new Coefficient();

            NamedNodeMap attributes = nodeList.get(i).getAttributes();

            coefficient.setLowBorder(new BigDecimal(attributes.getNamedItem("from").getNodeValue()));

            if (attributes.getNamedItem("to").getNodeValue().equals(INFINITY)) {
                coefficient.setUpperBorder(new BigDecimal(INFINITY_VALUE));
            } else {
                coefficient.setUpperBorder(new BigDecimal(attributes.getNamedItem("to").getNodeValue()));
            }

            coefficient.setValue(new BigDecimal(attributes.getNamedItem("value").getNodeValue()));

            coefficientList.add(coefficient);
        }

        LOGGER.debug("Building coefficients end");
        return coefficientList;

    }
}
