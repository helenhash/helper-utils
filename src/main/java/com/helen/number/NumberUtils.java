package com.helen.number;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@UtilityClass
public class NumberUtils {

    /**
     * Round bigDecimal number with some decimal places.
     * @param number
     * @param decimal
     * @return
     */
    public static BigDecimal roundNumber(BigDecimal number, int decimal) {
        return number.setScale(decimal, RoundingMode.HALF_UP);
    }

    public static BigDecimal roundNumber(double number, int decimal) {
        BigDecimal bigDecNum = doubleToDecimal(number);
        return bigDecNum.setScale(decimal, RoundingMode.HALF_UP);
    }

    public static BigDecimal doubleToDecimal(double d){
        BigDecimal b = new BigDecimal(d, MathContext.DECIMAL64);
        return b;
    }

    public static String formatNumber(BigDecimal number, int dec) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(number);
    }

}
