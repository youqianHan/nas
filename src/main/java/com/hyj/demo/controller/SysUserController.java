package com.hyj.demo.controller;

import com.hyj.demo.common.RestResponse;
import com.hyj.demo.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SysUserController {
    private final SysUserService sysUserService;
    /**
     * 查询
     */
    @GetMapping("/find")
    public RestResponse find(int id){
        return RestResponse.success(sysUserService.load(id));
    }
}
