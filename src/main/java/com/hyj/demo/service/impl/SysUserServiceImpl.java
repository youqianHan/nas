package com.hyj.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyj.demo.bo.AddSysUserBO;
import com.hyj.demo.bo.UpdateSysUserBO;
import com.hyj.demo.common.RestResponse;
import com.hyj.demo.common.enums.Status;
import com.hyj.demo.dto.SysUserDTO;
import com.hyj.demo.entity.SysUser;
import com.hyj.demo.mapper.SysUserMapper;
import com.hyj.demo.service.SysUserService;
import com.hyj.demo.vo.SysUserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {
    private final SysUserMapper sysUserMapper;

    @Override
    public RestResponse<String> insert(AddSysUserBO addSysUserBO) {
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(addSysUserBO,sysUser, CopyOptions.create().ignoreNullValue());
        this.save(sysUser);
        return RestResponse.success();
    }

    @Override
    public RestResponse<String> delete(int id) {
        int i = sysUserMapper.deleteById(id);
        Assert.isTrue(i>0, Status.ENTITY_NOT_EXISTS.getMessage());
        return RestResponse.success();
    }

    @Override
    public RestResponse<String> update(UpdateSysUserBO updateSysUserBO) {
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(updateSysUserBO,sysUser, CopyOptions.create().ignoreNullValue());
        boolean b = updateById(sysUser);
        Assert.isTrue(b, Status.ENTITY_NOT_EXISTS.getMessage());
        return RestResponse.success();
    }

    @Override
    public SysUserVO load(int id) {
        SysUserVO sysUserVO = new SysUserVO();
        SysUser sysUser = sysUserMapper.loadSysUser(id);
        BeanUtil.copyProperties(sysUser,sysUserVO,CopyOptions.create().ignoreNullValue());
        return sysUserVO;
    }

    @Override
    public Map<String, Object> pageList(int offset, int pagesize) {
        return null;
    }


}
