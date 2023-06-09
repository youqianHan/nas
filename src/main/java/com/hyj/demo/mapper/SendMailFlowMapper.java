package com.hyj.demo.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.hyj.demo.entity.SendMailFlow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SendMailFlowMapper extends MPJBaseMapper<SendMailFlow> {
}
