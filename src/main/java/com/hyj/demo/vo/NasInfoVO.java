package com.hyj.demo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hyj.demo.util.FileUnitConvert;
import com.hyj.demo.util.NasFileUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @description nas_info
 * @author rich
 * @date 2023-05-30 17:26:20
 */
@Data
@ApiModel("nas信息响应参数")
public class NasInfoVO {

    @ApiModelProperty(value = "主键",hidden = true)
    private Long id;

    @ApiModelProperty("路径")
    private String narsPath;

    @ApiModelProperty("总空间")
    private BigDecimal narsSize;

    @ApiModelProperty("已用空间")
    private BigDecimal narsUsed;

    @ApiModelProperty("可用空间")
    private BigDecimal narsAvail;

    @ApiModelProperty("总空间")
    private String narsSizeStr;

    @ApiModelProperty("已用空间")
    private String narsUsedStr;

    @ApiModelProperty("可用空间")
    private String narsAvailStr;

    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty("更新人")
    private String updateBy;

    @ApiModelProperty("所属组")
    private String userGroup;
    @ApiModelProperty("关联用户id")
    private Integer linkUserId;
    @ApiModelProperty("关联用户姓名")
    private String linkUserName;
    @ApiModelProperty("关联用户soeid")
    private String linkUserSoeid;

    public String getNarsSizeStr() {
        if(Objects.nonNull(narsSize)){
            return NasFileUtils.convertMemory(narsSize.longValue());
        }
        return narsSizeStr;
    }

    public String getNarsUsedStr() {
        if(Objects.nonNull(narsUsed)){
            return NasFileUtils.convertMemory(narsUsed.longValue());
        }
        return narsUsedStr;
    }

    public String getNarsAvailStr() {
        if(Objects.nonNull(narsAvail)){
            return NasFileUtils.convertMemory(narsAvail.longValue());
        }
        return narsAvailStr;
    }
}
