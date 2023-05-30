package com.hyj.demo.service;

import com.hyj.demo.common.RestResponse;
import com.hyj.demo.entity.NasInfo;

import java.util.Map;

/**
 * @description nas_info
 * @author rich
 * @date 2023-05-30 17:26:20
 */
public interface NasInfoService {

    /**
     * 新增
     */
    public RestResponse<String> insert(NasInfo nasInfo);

    /**
     * 删除
     */
    public RestResponse<String> delete(int id);

    /**
     * 更新
     */
    public RestResponse<String> update(NasInfo nasInfo);

    /**
     * 根据主键 id 查询
     */
    public NasInfo load(int id);

    /**
     * 分页查询
     */
    public Map<String,Object> pageList(int offset, int pagesize);

}
