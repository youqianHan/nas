package com.hyj.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description &
 * @author rich
 * @date 2023-05-30 17:31:26
 */
@Data
@ApiModel("SysUserNasDTO")
public class SysUserNasDTO {

    @ApiModelProperty(value = "主键",hidden = true)
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("nas盘id")
    private Long nasId;

    @ApiModelProperty("更新人")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    private Date updateBy;



}

