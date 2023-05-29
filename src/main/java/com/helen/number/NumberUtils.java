package com.helen.number;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@UtilityClass
public class NumberUtils {

    /**
     * Round bigDecimal number with some decimal places.
     *
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

    public static BigDecimal doubleToDecimal(double d) {
        BigDecimal b = new BigDecimal(d, MathContext.DECIMAL64);
        return b;
    }

    public boolean isDeepEqual(BigDecimal value1, BigDecimal value2) {
        if (value1 == null && value2 == null) {
            return true;
        }
        return value1 != null && value1.equals(value2);
    }

    public boolean isGreater0(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isLessThan0(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) < 0;
    }

    public BigDecimal subtract(BigDecimal value1, BigDecimal value2) {
        value1 = ObjectUtils.defaultIfNull(value1, BigDecimal.ZERO);
        value2 = ObjectUtils.defaultIfNull(value2, BigDecimal.ZERO);
        return value1.subtract(value2);
    }

    public BigDecimal subtractAbs(BigDecimal value1, BigDecimal value2) {
        value1 = ObjectUtils.defaultIfNull(value1, BigDecimal.ZERO);
        value2 = ObjectUtils.defaultIfNull(value2, BigDecimal.ZERO);
        return value1.subtract(value2).abs();
    }

    public BigDecimal divide(BigDecimal value1, BigDecimal value2) {
        value1 = ObjectUtils.defaultIfNull(value1, BigDecimal.ZERO);
        return (value2 == null || BigDecimal.ZERO.compareTo(value2) == 0)
                ? null
                : (value1.divide(value2, 10, RoundingMode.HALF_UP));
    }

    public BigDecimal add(BigDecimal value1, BigDecimal value2) {
        value1 = ObjectUtils.defaultIfNull(value1, BigDecimal.ZERO);
        value2 = ObjectUtils.defaultIfNull(value2, BigDecimal.ZERO);
        return value1.add(value2);
    }

    public BigDecimal add(BigDecimal... n) {
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal i : n) {
            if (i != null) {
                result = result.add(i);
            }
        }
        return result;
    }

    public BigDecimal subtract(BigDecimal minuend, BigDecimal... n) {
        minuend = ObjectUtils.defaultIfNull(minuend, BigDecimal.ZERO);
        return minuend.subtract(add(n));
    }

    public BigDecimal multiply(BigDecimal value1, BigDecimal value2) {
        value1 = ObjectUtils.defaultIfNull(value1, BigDecimal.ZERO);
        value2 = ObjectUtils.defaultIfNull(value2, BigDecimal.ZERO);
        return value1.multiply(value2);
    }

    public BigDecimal round1000(BigDecimal value) {
        value = ObjectUtils.defaultIfNull(value, BigDecimal.ZERO);
        return divide(value, BigDecimal.valueOf(1000));
    }

    public BigDecimal multiply1000(BigDecimal value) {
        value = ObjectUtils.defaultIfNull(value, BigDecimal.ZERO);
        return multiply(value, BigDecimal.valueOf(1000));
    }

    public BigDecimal percent(BigDecimal amount1, BigDecimal amount2) {
        BigDecimal ratio = divide(amount1, amount2);
        return (ratio == null) ? null :
                ratio.multiply(BigDecimal.valueOf(100))
                        .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal abs(BigDecimal value) {
        if (value == null) {
            return BigDecimal.ZERO;
        } else {
            return value.abs();
        }
    }

    public String formatNumberWithUnit(BigDecimal number, String formatType, int scale, String defIfNull) {
        String result;
        String formatTypeString;
        if (number == null) return defIfNull;
        result = formatNumber(number, scale, defIfNull == null ? "NA" : defIfNull);
        formatTypeString = switch (formatType) {
            case NumberConstants.PERCENT_TYPE -> result + "%";
            case NumberConstants.CURRENCY_TYPE -> "$" + result;
            case NumberConstants.X_TYPE -> result + "x";
            default -> result;
        };
        return formatTypeString;
    }

    public String formatNumber(BigDecimal number, int scale, String defIfNull) {
        BigDecimal decimal = new BigDecimal(1000);
        String result;
        if (number == null) return defIfNull == null ? "NA" : defIfNull;
        if (number.abs().compareTo(decimal) >= 1) {
            result = formatDecimal(number, scale, "#,###.##", defIfNull);
        } else {
            result = formatDecimal(number, scale, "#,##0.0", defIfNull);
        }
        if (isLessThan0(number)) {
            String negativeFormat = "(%s)";
            result = String.format(negativeFormat, result);
        }
        return result;
    }

    public static String formatDecimal(BigDecimal number, int scale, String format, String defIfNull) {
        if (number == null) return defIfNull == null ? "NA" : defIfNull;
        DecimalFormat formatter = new DecimalFormat(format);
        number = number.abs().setScale(scale, RoundingMode.HALF_UP);
        return formatter.format(number);
    }

}
