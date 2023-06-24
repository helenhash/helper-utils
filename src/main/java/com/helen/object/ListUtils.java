package com.helen.object;

import com.helen.number.NumberUtils;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

@UtilityClass
public class ListUtils {

    public static <T> List<T> mergeCollections(Collection<T>... lists) {
        Set<T> result = new HashSet<>();
        for (Collection<T> list : lists)
            result.addAll(list);
        return new ArrayList<>(result);
    }

    @SafeVarargs
    public static <T> List<T> mergeObjects(T... objects) {
        Set<T> result = new HashSet<>();
        for (T object : objects)
            result.add(object);
        return new ArrayList<>(result);
    }

    public static Object maxOf(Collection<Object> numbers) {
        BigDecimal maxValue = null;
        Object result = null;
        for (Object number : numbers) {
            if (number != null) {
                BigDecimal v = NumberUtils.toBigDecimal(number);
                if (v != null && (maxValue == null || maxValue.compareTo(v) < 0)) {
                    result = number;
                    maxValue = v;
                }
            }
        }
        return result;
    }

    public static <T> T findMax(Collection<T> values, Function<T, Object> numberGetter) {
        BigDecimal maxValue = null;
        T result = null;
        for (T value : values) {
            if (value != null) {
                Object number = numberGetter.apply(value);
                BigDecimal v = NumberUtils.toBigDecimal(number);
                if (v != null && (maxValue == null || maxValue.compareTo(v) < 0)) {
                    result = value;
                    maxValue = v;
                }
            }
        }
        return result;
    }

}
