package com.hyj.demo.bo.common;

import com.hyj.demo.vo.common.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 公共字段
 */
@Data
public class BaseBO extends PageRequest {


    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty("更新人")
    private String updateBy;

}
