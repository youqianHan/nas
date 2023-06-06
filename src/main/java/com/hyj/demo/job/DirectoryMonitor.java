package com.hyj.demo.job;

import com.hyj.demo.bo.common.QueryNasBO;
import com.hyj.demo.common.entity.EmailInfo;
import com.hyj.demo.dto.NasInfoDTO;
import com.hyj.demo.handler.SendEmailHandler;
import com.hyj.demo.service.impl.NasInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class DirectoryMonitor {
    private final NasInfoServiceImpl nasInfoService;
    @Value("${monitor.threshold.size}")
    private long thresholdSize;
    private final SendEmailHandler emailHandler;





    @Scheduled(cron ="${monitor.job.cron}") // 每分钟执行一次监控任务
    public void monitorDirectoryUsage() {
        //查询出所有已关联用户的nas目录 (未关联用户的目录不需要监控)
        List<NasInfoDTO> nasList = nasInfoService.getNasList(new QueryNasBO());
        Map<String, List<NasInfoDTO>> nasInfoMap = 
                nasList.stream().
                filter(item -> Objects.nonNull(item.getLinkUserId()))
                .collect(Collectors.groupingBy(NasInfoDTO::getNarsPath));

        //遍历目录并监控
        nasInfoMap.forEach((filePath,v)->{
            File directory = new File(filePath);
            long directorySize = FileUtils.sizeOf(directory);
            long directoryMbSize = directorySize / (1024 * 1024);
            if (directoryMbSize > thresholdSize) {
                //根据email去重
                List<NasInfoDTO> distinctNasInfoDTOS = v.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                        -> new TreeSet<>(Comparator.comparing(NasInfoDTO :: getLinkUserEmail))), ArrayList::new));
                //给所有关联这个目录的人发送邮件
                for (NasInfoDTO distinctNasInfoDTO : distinctNasInfoDTOS) {
                    //构建邮件基础信息
                    EmailInfo emailInfo = EmailInfo.builder().emailAddress(distinctNasInfoDTO.getLinkUserEmail())
                            .subject("空间超出阈值预警").username(distinctNasInfoDTO.getLinkUserName()).build();
                    //发送邮件
                    log.info("{}空间超出阈值预警 sendWarningEmail",filePath);
                    emailHandler.handler(filePath, directorySize,emailInfo );
                }

            }
        });

    }



}
