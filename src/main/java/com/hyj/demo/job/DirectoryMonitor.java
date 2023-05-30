package com.hyj.demo.job;

import com.hyj.demo.handler.SendEmailHandler;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DirectoryMonitor {
    private final JavaMailSender mailSender;
    @Value("${monitor.directories}")
    private List<String> directories;
    @Value("${monitor.threshold.size}")
    private long thresholdSize;
    private final SendEmailHandler emailHandler;





    @Scheduled(fixedRate = 60000) // 每分钟执行一次监控任务
    public void monitorDirectoryUsage() {
        for (String directoryPath : directories) {
            File directory = new File(directoryPath);
            long directorySize = FileUtils.sizeOf(directory);

            if (directorySize > thresholdSize) {
                emailHandler.handler(directoryPath, directorySize);
            }
        }
    }



}
