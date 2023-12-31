package com.hyj.demo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("更新nas参数")
@Data
public class UpdateNasInfoBO {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("路径")
    private String narsPath;

    @ApiModelProperty("总空间")
    private BigDecimal narsSize;

    @ApiModelProperty("已用空间")
    private BigDecimal narsUsed;

    @ApiModelProperty("可用空间")
    private BigDecimal narsAvail;

    @ApiModelProperty("所属组")
    private String userGroup;
    @ApiModelProperty("关联用户")
    private Long linkUserId;
}
