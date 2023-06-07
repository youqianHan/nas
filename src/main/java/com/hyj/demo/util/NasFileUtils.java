package com.hyj.demo.util;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class NasFileUtils {


    /**
     * 根据路径获取该目录所占空间大小
     * @param filePath
     * @return
     */
    public static Long getDirectorySizeByFilePath(String filePath){
        File directory = new File(filePath);
        long directorySize = FileUtils.sizeOf(directory);
        long directoryMbSize = directorySize / (1024 * 1024);
        return directoryMbSize;
    }
}
