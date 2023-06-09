package com.hyj.demo.service;

import com.hyj.demo.entity.SendMailFlow;

import java.util.List;
import java.util.Map;

public interface SendMailFlowService {
    /**
     * 新增
     */
    public void insert(SendMailFlow sendMailFlow);

    /**
     * 删除
     */
    public Object delete(int id);

    /**
     * 更新
     */
    public Object update(SendMailFlow sendMailFlow);

    /**
     * 根据主键 id 查询
     */
    public SendMailFlow load(int id);

    /**
     * 分页查询
     */
    public Map<String,Object> pageList(int offset, int pagesize);

    List<SendMailFlow> loadBySoeid(String soeid);
}
