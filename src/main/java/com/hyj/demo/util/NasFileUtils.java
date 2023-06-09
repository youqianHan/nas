package com.hyj.demo.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.DecimalFormat;

public class NasFileUtils {


    /**
     * 根据路径获取该目录所占空间大小
     * @param filePath
     * @return
     */
    public static Long getDirectorySizeByFilePath(String filePath){
        File directory = new File(filePath);
        long directorySize = FileUtils.sizeOf(directory);
//        long directoryMbSize = directorySize / (1024 * 1024);
        return directorySize;
    }


    public static String convertMemory(long memoryInBytes) {
        if (memoryInBytes < 1024) {
            return memoryInBytes + "B";
        }

        double memory = memoryInBytes;
        String[] units = {"B", "KB", "MB", "GB", "TB"};

        int unitIndex = 0;
        while (memory >= 1024 && unitIndex < units.length - 1) {
            memory /= 1024;
            unitIndex++;
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(memory) + units[unitIndex];
    }
    public static long convertMBtoBytes(long megabytes) {
        return megabytes * 1024 * 1024;
    }
}
