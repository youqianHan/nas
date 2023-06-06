package com.hyj.demo.vo;

import lombok.Data;

import java.util.List;

@Data
public class SysUserSelectVO {
    private String userGroup;
    private List<SysUserVO> sysUserVos;
}
