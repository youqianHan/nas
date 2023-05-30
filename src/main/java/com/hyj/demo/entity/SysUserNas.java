package com.hyj.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description &
 * @author rich
 * @date 2023-05-30 17:31:26
 */
@Data
public class SysUserNas implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * nas盘id
     */
    private Integer nasId;

    /**
     * 更新人
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date updateBy;

    public SysUserNas() {
    }

}

