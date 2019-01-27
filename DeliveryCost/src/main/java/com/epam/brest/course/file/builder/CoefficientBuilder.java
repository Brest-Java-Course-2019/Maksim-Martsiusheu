package com.epam.brest.course.file.builder;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.math.BigDecimal;

public class CoefficientBuilder {
    private final static String INFINITY = "infinity";
    private final static String INFINITY_VALUE = "10e30";

    public Coefficient createCoefficient(Node currentNode) throws NumberFormatException {

        NamedNodeMap attributes = currentNode.getAttributes();

        Coefficient coefficient = new Coefficient();
        coefficient.setLowBorder(new BigDecimal(attributes.getNamedItem("from").getNodeValue()));

        if (attributes.getNamedItem("to").getNodeValue().equals(INFINITY)) {
            coefficient.setUpperBorder(new BigDecimal(INFINITY_VALUE));
        } else {
            coefficient.setUpperBorder(new BigDecimal(attributes.getNamedItem("to").getNodeValue()));
        }

        coefficient.setValue(new BigDecimal(attributes.getNamedItem("value").getNodeValue()));

        return coefficient;

    }
}
