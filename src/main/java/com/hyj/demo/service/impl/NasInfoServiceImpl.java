package com.hyj.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyj.demo.bo.AddNasInfoBO;
import com.hyj.demo.bo.UpdateNasInfoBO;
import com.hyj.demo.common.RestResponse;
import com.hyj.demo.common.enums.Status;
import com.hyj.demo.dto.NasInfoDTO;
import com.hyj.demo.entity.NasInfo;
import com.hyj.demo.entity.SysUser;
import com.hyj.demo.mapper.NasInfoMapper;
import com.hyj.demo.service.NasInfoService;
import com.hyj.demo.vo.NasInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description nas_info
 * @author rich
 * @date 2023-05-30 17:26:20
 */
@Service
@RequiredArgsConstructor
public class NasInfoServiceImpl extends ServiceImpl<NasInfoMapper,NasInfo> implements NasInfoService {

    private final NasInfoMapper nasInfoMapper;


    @Override
    public RestResponse<String> insert(AddNasInfoBO addNasInfoBO) {
        NasInfo nasInfo = new NasInfo();
        BeanUtil.copyProperties(addNasInfoBO,nasInfo, CopyOptions.create().ignoreNullValue());
        this.save(nasInfo);
        return RestResponse.success();
    }

    @Override
    public RestResponse<String> delete(int id) {
        int i = nasInfoMapper.deleteById(id);
        Assert.isTrue(i>0, Status.ENTITY_NOT_EXISTS.getMessage());
        return RestResponse.success();
    }

    @Override
    public RestResponse<String> update(UpdateNasInfoBO updateNasInfoBO) {
        NasInfo nasInfo = new NasInfo();
        BeanUtil.copyProperties(updateNasInfoBO,nasInfo, CopyOptions.create().ignoreNullValue());
        boolean b = updateById(nasInfo);
        Assert.isTrue(b, Status.ENTITY_NOT_EXISTS.getMessage());
        return RestResponse.success();
    }

    @Override
    public NasInfoVO load(int id) {
        NasInfo nasInfo = nasInfoMapper.loadNasInfo(id);
        NasInfoVO nasInfoVO = new NasInfoVO();
        BeanUtil.copyProperties(nasInfo,nasInfoVO,CopyOptions.create().ignoreNullValue());
        return nasInfoVO;
    }

    @Override
    public Map<String, Object> pageList(int offset, int pagesize) {
        return null;
    }
}
