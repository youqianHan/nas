package com.hyj.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyj.demo.common.RestResponse;
import com.hyj.demo.common.enums.Status;
import com.hyj.demo.dto.SysUserNasDTO;
import com.hyj.demo.entity.SysUser;
import com.hyj.demo.entity.SysUserNas;
import com.hyj.demo.mapper.SysUserMapper;
import com.hyj.demo.mapper.SysUserNasMapper;
import com.hyj.demo.service.SysUserNasService;
import com.hyj.demo.service.SysUserService;
import com.hyj.demo.vo.SysUserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SysUserNasServiceImpl  extends ServiceImpl<SysUserNasMapper, SysUserNas> implements SysUserNasService {
    @Override
    public RestResponse<String> insert(SysUserNasDTO sysUserNasDTO) {
        SysUserNas sysUserNas = new SysUserNas();
        BeanUtil.copyProperties(sysUserNasDTO,sysUserNas, CopyOptions.create().ignoreNullValue());
        this.save(sysUserNas);
        return RestResponse.success();
    }

    @Override
    public RestResponse<String> delete(int id) {
        return null;
    }

    @Override
    public RestResponse<String> update(SysUserNasDTO sysUserNasDTO) {
        SysUserNas sysUserNas = new SysUserNas();
        BeanUtil.copyProperties(sysUserNasDTO,sysUserNas, CopyOptions.create().ignoreNullValue());
        boolean b = updateById(sysUserNas);
        Assert.isTrue(b, Status.ENTITY_NOT_EXISTS.getMessage());
        return RestResponse.success();
    }

    @Override
    public SysUserNas load(int id) {
        return null;
    }

    @Override
    public SysUserNas loadByNasId(Long id) {
        return this.getOne(new LambdaQueryWrapper<SysUserNas>().eq(SysUserNas::getNasId,id));
    }

    @Override
    public Map<String, Object> pageList(int offset, int pagesize) {
        return null;
    }

    @Override
    public Boolean updateLinkUserByNasId(Long userId,Long nasId) {
        this.update(new LambdaUpdateWrapper<SysUserNas>().set(SysUserNas::getUserId,userId)
                .eq(SysUserNas::getNasId,nasId));
        return null;
    }
}
