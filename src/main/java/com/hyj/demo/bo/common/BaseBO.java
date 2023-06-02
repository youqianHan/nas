package com.hyj.demo.bo.common;

import com.hyj.demo.vo.common.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 公共字段
 */
@Data
public class BaseBO extends PageRequest {
    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("更新人")
    private Date updateBy;

}
