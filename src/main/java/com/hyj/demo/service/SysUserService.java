package com.hyj.demo.service;

import com.hyj.demo.common.RestResponse;
import com.hyj.demo.entity.SysUser;

import java.util.Map;

/**
 * @description sys_user
 * @author rich
 * @date 2023-05-30 17:12:41
 */
public interface SysUserService {

    /**
     * 新增
     */
    public RestResponse<String> insert(SysUser sysUser);

    /**
     * 删除
     */
    public RestResponse<String> delete(int id);

    /**
     * 更新
     */
    public RestResponse<String> update(SysUser sysUser);

    /**
     * 根据主键 id 查询
     */
    public SysUser load(int id);

    /**
     * 分页查询
     */
    public Map<String,Object> pageList(int offset, int pagesize);

}
