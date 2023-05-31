package com.hyj.demo.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.hyj.demo.entity.SysUserNas;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @description &
 * @author rich
 * @date 2023-05-30 17:31:26
 */
@Mapper
@Repository
public interface SysUserNasMapper extends MPJBaseMapper<SysUserNas> {

    /**
     * 新增
     * @author rich
     * @date 2023/05/30
     **/
    int insertSysUserNas(SysUserNas sysUserNas);

    /**
     * 刪除
     * @author rich
     * @date 2023/05/30
     **/
    int deleteSysUserNas(int id);

    /**
     * 更新
     * @author rich
     * @date 2023/05/30
     **/
    int updateSysUserNas(SysUserNas sysUserNas);

    /**
     * 查询 根据主键 id 查询
     * @author rich
     * @date 2023/05/30
     **/
    SysUserNas loadSysUserNas(int id);

    /**
     * 查询 分页查询
     * @author rich
     * @date 2023/05/30
     **/
    List<SysUserNas> pageListSysUserNas(Map<String,Object> map);

    /**
     * 查询 分页查询 count
     * @author rich
     * @date 2023/05/30
     **/
    int pageListCountSysUserNas(Map<String,Object> map);

}
