package com.hyj.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description nas_info
 * @author rich
 * @date 2023-05-30 17:26:20
 */
@Data
@TableName("nas_info")
public class NasInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 路径
     */
    private String narsPath;

    /**
     * 总空间
     */
    private BigDecimal narsSize;

    /**
     * 已用空间
     */
    private BigDecimal narsUsed;

    /**
     * 可用空间
     */
    private BigDecimal narsAvail;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 所属组
     */
    private String userGroup;
    /**
     * 关联用户id
     */
    @TableField(exist = false)
    private Long linkUserId;

    @TableField(exist = false)
    private String linkUserName;

    @TableField(exist = false)
    private String linkUserSoeid;
    @TableField(exist = false)
    private String linkUserEmail;
    public NasInfo() {
    }

}
