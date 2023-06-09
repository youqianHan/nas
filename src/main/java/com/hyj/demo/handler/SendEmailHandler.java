package com.hyj.demo.handler;

import com.hyj.demo.common.entity.EmailInfo;
import com.hyj.demo.entity.SendMailFlow;
import com.hyj.demo.service.SendMailFlowService;
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

    @Value("${monitor.threshold.size}")
    private long thresholdSize;
    @Value("${spring.mail.username}")
    private String username;
    private final JavaMailSender mailSender;
    private final SendMailFlowService sendMailFlowService;

    @Override
    public void handler(String directoryPath, String directorySize, EmailInfo emailInfo) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        List<String> exceedingFiles = new ArrayList<>();

        for (File file : files) {
            if (file.isFile()) {
                exceedingFiles.add(file.getName());
            }
        }

        if (exceedingFiles.isEmpty()) {
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(emailInfo.getEmailAddress());
        message.setSubject(emailInfo.getSubject());
        message.setText("目录 " + directoryPath + " 的空间使用超过阀值，当前大小为: " + directorySize + " bytes\n" +
                "超过阀值的文件列表:\n" + String.join("\n", exceedingFiles));

        mailSender.send(message);
        //记录发送流水
        SendMailFlow sendMailFlow = SendMailFlow.builder().sendTo(emailInfo.getEmailAddress()).sendType("1")//不知道弄个什么type
                .subject(emailInfo.getSubject()).soeid(emailInfo.getSoeid()).content(message.getText()).build();
        sendMailFlowService.insert(sendMailFlow);
    }
}
