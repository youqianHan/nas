package com.hyj.demo.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SendEmailHandler implements EmailHandler{
    @Value("${monitor.directories}")
    private List<String> directories;

    @Value("${monitor.threshold.size}")
    private long thresholdSize;

    private final JavaMailSender mailSender;

    @Override
    public void handler(String directoryPath, long directorySize) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        List<String> exceedingFiles = new ArrayList<>();

        for (File file : files) {
            if (file.isFile() && file.length() > thresholdSize) {
                exceedingFiles.add(file.getName());
            }
        }

        if (exceedingFiles.isEmpty()) {
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("1360921711@qq.com");
        message.setSubject("目录空间使用超过阀值");
        message.setText("目录 " + directoryPath + " 的空间使用超过阀值，当前大小为: " + directorySize + " bytes\n" +
                "超过阀值的文件列表:\n" + String.join("\n", exceedingFiles));

        mailSender.send(message);
    }
}
