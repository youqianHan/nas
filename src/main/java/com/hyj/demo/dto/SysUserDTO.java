package com.hyj.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description sys_user
 * @author rich
 * @date 2023-05-30 17:12:41
 */
@Data
@ApiModel("sys_user")
public class SysUserDTO {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("soeid工号")
    private String soeid;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("更新人")
    private Date updateBy;

    @ApiModelProperty("所属组")
    private String group;


}
