package com.hyj.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @description send_mail_flow
 * @author rich
 * @date 2023-06-09
 */
@Data
@TableName("send_mail_flow")
@ApiModel("send_mail_flow")
@Builder
@AllArgsConstructor
public class SendMailFlow implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 序列号
     */
    @ApiModelProperty("序列号")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 收件人soeid
     */
    @ApiModelProperty("收件人soeid")
    private String soeid;

    /**
     * 收件人email
     */
    @ApiModelProperty("收件人email")
    private String sendTo;

    /**
     * 发送告警类型
     */
    @ApiModelProperty("发送告警类型")
    private String sendType;

    /**
     * 邮件标题
     */
    @ApiModelProperty("邮件标题")
    private String subject;

    /**
     * 邮件内容
     */
    @ApiModelProperty("邮件内容")
    private String content;

    /**
     * 发送时间
     */
    @ApiModelProperty("发送时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String memo;

    public SendMailFlow() {
    }

}