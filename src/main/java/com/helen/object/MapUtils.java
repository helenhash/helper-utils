package com.helen.object;

import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.function.Function;

@UtilityClass
public class MapUtils {

    /**
     * List to map with provided key.
     * @param values
     * @param keyGetter
     * @param <K>
     * @param <T>
     * @return
     */
    public static <K, T> Map<K, T> toMap(Collection<T> values, Function<T, K> keyGetter) {
        Map<K, T> result = new HashMap<>();
        values.forEach(t -> result.put(keyGetter.apply(t), t));
//        result = values.stream().collect(Collectors.toMap(keyGetter, c -> c));
        return result;
    }

    /**
     * Group a list by key.
     * @param values
     * @param keyGetter
     * @param <K>
     * @param <T>
     * @return
     */
    public static <K, T> Map<K, List<T>> toGroupMap(Collection<T> values, Function<T, K> keyGetter) {
        Map<K, List<T>> result = new HashMap<>();
        values.forEach(t -> {
            K key = keyGetter.apply(t);
            if(result.containsKey(key)){
                result.get(key).add(t);
            } else{
                List<T> l = new ArrayList<>();
                l.add(t);
                result.put(key, l);
            }
        });
//        result = values.stream().collect(groupingBy(t -> keyGetter.apply(t)));
        return result;
    }

}
