package com.helen.file;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

@UtilityClass
public class FileUtils {

    /**
     * Create file in temp folder.
     * @param prefix
     *            the file name.
     * @param suffix
     *            the extension
     * @return File
     */
    public static File createTempFile(String prefix, String suffix) {
        String fileName = (StringUtils.isEmpty(prefix) ? "FILE" : prefix) +
                (StringUtils.isEmpty(suffix) ? ".csv" : suffix);
        String tempDir = System.getProperty("java.io.tmpdir");
        return new File(tempDir, fileName);
    }

}
