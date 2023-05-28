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
	
	public BigDecimal calculatePercentage(BigDecimal amount1, BigDecimal amount2) {
		BigDecimal ratio = divide(amount1, amount2);
		return (ratio == null) ? null :
				ratio.multiply(BigDecimal.valueOf(100))
				.setScale(2, RoundingMode.HALF_UP);
	}
	

}
