package com.epam.brest.course.selector;

import com.epam.brest.course.file.builder.Coefficient;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CoefficientSelector {
    public Coefficient selectCoefficient(ArrayList<Coefficient> coefficientList, BigDecimal param) {
        Coefficient coefficient = null;
        int i = 0;
        boolean isCoefficientChose = false;
        while (!isCoefficientChose && i < coefficientList.size()) {
            if (isCorrectCoefficient(coefficientList.get(i), param)) {
                coefficient = coefficientList.get(i);
                isCoefficientChose = true;
            }
            i++;
        }
        return coefficient;
    }

    private boolean isCorrectCoefficient(Coefficient coefficient, BigDecimal param) {
        return param.compareTo(coefficient.getLowBorder()) >= 0 &&
                param.compareTo(coefficient.getUpperBorder()) < 0;
    }
}
