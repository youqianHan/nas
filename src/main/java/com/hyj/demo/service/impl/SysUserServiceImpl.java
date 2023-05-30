package com.hyj.demo.service.impl;

import com.hyj.demo.common.RestResponse;
import com.hyj.demo.entity.SysUser;
import com.hyj.demo.mapper.SysUserMapper;
import com.hyj.demo.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {
    private final SysUserMapper sysUserMapper;
    @Override
    public RestResponse<String> insert(SysUser sysUser) {
        return null;
    }

    @Override
    public RestResponse<String> delete(int id) {
        return null;
    }

    @Override
    public RestResponse<String> update(SysUser sysUser) {
        return null;
    }

    @Override
    public SysUser load(int id) {
        return sysUserMapper.loadSysUser(id);
    }

    @Override
    public Map<String, Object> pageList(int offset, int pagesize) {
        return null;
    }
}
