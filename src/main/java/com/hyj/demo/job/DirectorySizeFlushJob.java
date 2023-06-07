package com.hyj.demo.job;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hyj.demo.entity.NasInfo;
import com.hyj.demo.service.impl.NasInfoServiceImpl;
import com.hyj.demo.util.NasFileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class DirectorySizeFlushJob {
    private final NasInfoServiceImpl nasInfoService;
    @Value("${monitor.threshold.size}")
    private long thresholdSize;




    @Scheduled(cron ="${monitor.job.flush-size-cron}") // 每分钟执行一次监控任务
    public void flushDirectorySize() {
    //读取所有去重后的目录
        List<NasInfo> distinctNasInfos = nasInfoService.list().stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(NasInfo :: getNarsPath))), ArrayList::new));

        //根据目录update 已用  、可用(总-已用)、总空间(暂时统一为一个阈值  后续可根据目录不同 灵活扩展)
        //读取每个目录的当前已用空间
        for (NasInfo nasInfo : distinctNasInfos) {
            Long narsUsed = NasFileUtils.getDirectorySizeByFilePath(nasInfo.getNarsPath());//已用空间
            Long narsAvail = (thresholdSize-narsUsed)>0?thresholdSize-narsUsed:0;//剩余空间
            nasInfoService.update(new LambdaUpdateWrapper<NasInfo>().set(NasInfo::getNarsSize,thresholdSize).set(NasInfo::getNarsUsed,narsUsed)
                    .set(NasInfo::getNarsAvail,narsAvail).eq(NasInfo::getNarsPath,nasInfo.getNarsPath()));
        }


    }
}
