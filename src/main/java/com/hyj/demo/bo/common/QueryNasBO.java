package com.hyj.demo.bo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel("新增用户参数")
@Data
public class QueryNasBO extends BaseBO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty("路径")
    private String narsPath;

    @ApiModelProperty("总空间")
    private BigDecimal narsSize;

    @ApiModelProperty("已用空间")
    private BigDecimal narsUsed;

    @ApiModelProperty("可用空间")
    private BigDecimal narsAvail;

    @ApiModelProperty("关联用户姓名")
    private String linkUserName;
    @ApiModelProperty("关联用户soeid")
    private String linkUserSoeid;

    @ApiModelProperty("所属组")
    private String userGroup;

}
