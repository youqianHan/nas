package com.hyj.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description sys_user
 * @author rich
 * @date 2023-05-30 17:12:41
 */
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * soeid工号
     */
    private String soeid;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
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
     * 更新人
     */
    private Date updateBy;

    /**
     * 所属组
     */
    private String group;

    public SysUser() {
    }

}
