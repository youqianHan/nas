package com.hyj.demo.bo;

import com.hyj.demo.bo.common.BaseBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("更新用户参数")
@Data
public class UpdateSysUserBO extends BaseBO {
    @ApiModelProperty("唯一id")
    private String id;
    @ApiModelProperty("soeid工号")
    private String soeid;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("所属组")
    private String userGroup;

}
