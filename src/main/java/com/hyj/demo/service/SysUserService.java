package com.hyj.demo.service;

import com.hyj.demo.bo.AddSysUserBO;
import com.hyj.demo.bo.UpdateSysUserBO;
import com.hyj.demo.common.RestResponse;
import com.hyj.demo.dto.SysUserDTO;
import com.hyj.demo.entity.SysUser;
import com.hyj.demo.vo.SysUserVO;

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
    public RestResponse<String> insert(AddSysUserBO addSysUserBO);

    /**
     * 删除
     */
    public RestResponse<String> delete(int id);

    /**
     * 更新
     */
    public RestResponse<String> update(UpdateSysUserBO updateSysUserBO);

    /**
     * 根据主键 id 查询
     */
    public SysUserVO load(int id);

    /**
     * 分页查询
     */
    public Map<String,Object> pageList(int offset, int pagesize);

}
