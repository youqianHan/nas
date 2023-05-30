package com.hyj.demo.mapper;

import com.hyj.demo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @description sys_user
 * @author rich
 * @date 2023-05-30 17:12:41
 */
@Mapper
@Repository
public interface SysUserMapper {

    /**
     * 新增
     * @author rich
     * @date 2023/05/30
     **/
    int insertSysUser(SysUser sysUser);

    /**
     * 刪除
     * @author rich
     * @date 2023/05/30
     **/
    int deleteSysUser(int id);

    /**
     * 更新
     * @author rich
     * @date 2023/05/30
     **/
    int updateSysUser(SysUser sysUser);

    /**
     * 查询 根据主键 id 查询
     * @author rich
     * @date 2023/05/30
     **/
    SysUser loadSysUser(int id);

    /**
     * 查询 分页查询
     * @author rich
     * @date 2023/05/30
     **/
    List<SysUser> pageListSysUser(Map<String,Object> map);

    /**
     * 查询 分页查询 count
     * @author rich
     * @date 2023/05/30
     **/
    int pageListCountSysUser(Map<String,Object> map);

}
