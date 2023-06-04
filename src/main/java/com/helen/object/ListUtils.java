package com.helen.object;

import lombok.experimental.UtilityClass;

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

    public static <T> List<T> mergeObjects(T... objects) {
        Set<T> result = new HashSet<>();
        for (T object : objects)
            result.add(object);
        return new ArrayList<>(result);
    }

}
