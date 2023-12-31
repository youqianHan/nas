package com.hyj.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hyj.demo.bo.AddNasInfoBO;
import com.hyj.demo.bo.UpdateNasInfoBO;
import com.hyj.demo.bo.common.QueryNasBO;
import com.hyj.demo.common.RestResponse;
import com.hyj.demo.dto.NasInfoDTO;
import com.hyj.demo.entity.NasInfo;
import com.hyj.demo.vo.NasInfoVO;

import java.util.List;
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
    public RestResponse<String> insert(AddNasInfoBO addNasInfoBO);

    /**
     * 删除
     */
    public RestResponse<String> delete(List<Integer> ids);

    /**
     * 更新
     */
    public RestResponse<String> update(UpdateNasInfoBO updateNasInfoBO);

    /**
     * 根据主键 id 查询
     */
    public NasInfoVO load(int id);

    /**
     * 分页查询
     */
    IPage<NasInfoVO> getNasPage(QueryNasBO queryNasBO);

}
