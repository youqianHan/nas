package com.hyj.demo.service;

import com.hyj.demo.common.RestResponse;
import com.hyj.demo.dto.SysUserNasDTO;
import com.hyj.demo.entity.SysUserNas;

import java.util.Map;

/**
 * @description &
 * @author rich
 * @date 2023-05-31 10:27:31
 */
public interface SysUserNasService {

    /**
     * 新增
     */
    public RestResponse<String> insert(SysUserNasDTO sysUserNasDTO);

    /**
     * 删除
     */
    public RestResponse<String> delete(int id);

    /**
     * 更新
     */
    public RestResponse<String> update(SysUserNasDTO sysUserNasDTO);

    /**
     * 根据主键 id 查询
     */
    public SysUserNas load(int id);

    /**
     * 根据nas主键 id 查询
     */
    public SysUserNas loadByNasId(Long id);

    /**
     * 分页查询
     */
    public Map<String,Object> pageList(int offset, int pagesize);

    /**
     * 根据nas主键 id 查询
     */
    public Boolean updateLinkUserByNasId(Long userId,Long nasId);

}
