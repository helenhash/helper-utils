package com.helen.object;

import lombok.experimental.UtilityClass;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

@UtilityClass
public class ObjectUtils {

    public static Object cloneObject(Object obj) {
        Object objRt = null;
        try {
            objRt = BeanUtils.cloneBean(obj);
        } catch (IllegalAccessException e) {
            // log
        } catch (InstantiationException e) {
            // log
        } catch (InvocationTargetException e) {
            // log
        } catch (NoSuchMethodException e) {
            // log
        }
        return objRt;
    }

    /**
     * Copy all props from an object to another one (2 different class).
     * Remember this will copy the properties with the same name only.
     * @param targetObj
     * @param sourceObj
     * @return
     */
    public static Object copyObject(Object targetObj, Object sourceObj){
        try {
            BeanUtils.copyProperties(targetObj, sourceObj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return targetObj;
    }

}
