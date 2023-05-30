package com.hyj.demo.service.impl;

import com.hyj.demo.common.RestResponse;
import com.hyj.demo.entity.NasInfo;
import com.hyj.demo.mapper.NasInfoMapper;
import com.hyj.demo.service.NasInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description nas_info
 * @author rich
 * @date 2023-05-30 17:26:20
 */
@Service
public class NasInfoServiceImpl implements NasInfoService {

    @Resource
    private NasInfoMapper nasInfoMapper;


    @Override
    public RestResponse<String> insert(NasInfo nasInfo) {
        return null;
    }

    @Override
    public RestResponse<String> delete(int id) {
        return null;
    }

    @Override
    public RestResponse<String> update(NasInfo nasInfo) {
        return null;
    }

    @Override
    public NasInfo load(int id) {
        return null;
    }

    @Override
    public Map<String, Object> pageList(int offset, int pagesize) {
        return null;
    }
}
