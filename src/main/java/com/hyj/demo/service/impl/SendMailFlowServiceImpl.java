package com.hyj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyj.demo.entity.SendMailFlow;
import com.hyj.demo.mapper.SendMailFlowMapper;
import com.hyj.demo.service.SendMailFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description nas_info
 * @author rich
 * @date 2023-05-30 17:26:20
 */
@Service
@RequiredArgsConstructor
public class SendMailFlowServiceImpl extends ServiceImpl<SendMailFlowMapper, SendMailFlow> implements SendMailFlowService {


    @Override
    public void insert(SendMailFlow sendMailFlow) {
        this.save(sendMailFlow);
    }

    @Override
    public Object delete(int id) {
        return null;
    }

    @Override
    public Object update(SendMailFlow sendMailFlow) {
        return null;
    }

    @Override
    public SendMailFlow load(int id) {
        return null;
    }

    @Override
    public Map<String, Object> pageList(int offset, int pagesize) {
        return null;
    }

    public List<SendMailFlow> loadBySoeid(String soeid) {
        List<SendMailFlow> list = this.list(new LambdaQueryWrapper<SendMailFlow>().eq(SendMailFlow::getSoeid, soeid));
        return list;
    }

}
