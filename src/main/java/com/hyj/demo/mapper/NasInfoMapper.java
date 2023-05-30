package com.hyj.demo.mapper;

import com.hyj.demo.entity.NasInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @description nas_info
 * @author rich
 * @date 2023-05-30 17:26:20
 */
@Mapper
@Repository
public interface NasInfoMapper {

    /**
     * 新增
     * @author rich
     * @date 2023/05/30
     **/
    int insertNasInfo(NasInfo nasInfo);

    /**
     * 刪除
     * @author rich
     * @date 2023/05/30
     **/
    int deleteNasInfo(int id);

    /**
     * 更新
     * @author rich
     * @date 2023/05/30
     **/
    int updateNasInfo(NasInfo nasInfo);

    /**
     * 查询 根据主键 id 查询
     * @author rich
     * @date 2023/05/30
     **/
    NasInfo loadNasInfo(int id);

    /**
     * 查询 分页查询
     * @author rich
     * @date 2023/05/30
     **/
    List<NasInfo> pageListNasInfo(Map<String,Object> map);

    /**
     * 查询 分页查询 count
     * @author rich
     * @date 2023/05/30
     **/
    int pageListCountNasInfo(Map<String,Object> map);

}
