package com.helen.date;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class DateConstants {

    public final String YYYYMMDD = "yyyyMMdd";
    public final String DD_MM_YYYY = "dd-MM-yyyy";
    public final String YYYY_MM_DD = "yyyy-MM-dd";
    public final String MM_DD_YYYY = "MM/dd/yyyy";
    public final String YYYY_MM_DD_SLASH = "yyyy/MM/dd";
    public final String DD_MMM_YYYY = "dd MMM yyyy";
    public final String DD_MMMM_YYYY ="dd MMMM yyyy";

    public final String LONG_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss.SSS";
    public final String DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
    public final String TIME_FORMAT = "HH:mm:ss";

    public final List<String> SUPPORTED_DATE_FORMATS = Arrays.asList(
            MM_DD_YYYY,
            YYYYMMDD,
            DD_MM_YYYY,
            YYYY_MM_DD,
            YYYY_MM_DD_SLASH,
            DD_MMM_YYYY,
            DD_MMMM_YYYY
    );
}
