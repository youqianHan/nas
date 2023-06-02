package com.hyj.demo.vo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 分页请求参数
 * @Author hyj
 */
@Data
@ApiModel("分页参数")
public class PageRequest implements Serializable {

    @ApiModelProperty("页码")
    private Integer pageNo = 1;

    @ApiModelProperty("页大小")
    private Integer pageSize = 10;

    @ApiModelProperty("跳过x条数据")
    @JsonIgnore
    private Integer offset;

    @ApiModelProperty("排序列")
    private String orderByColumn;

    @ApiModelProperty("排序方向(desc 或者 asc)")
    private String order;

    public Integer getOffset() {
        if (offset == null) {
            return (pageNo - 1) * pageSize;
        }
        return offset;
    }

    public void buildPage(int pageNo, int pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.offset = (pageNo - 1) * pageSize;
    }

}
