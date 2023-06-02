
package com.hyj.demo.controller;

import com.hyj.demo.common.RestResponse;
import com.hyj.demo.dto.SysUserNasDTO;
import com.hyj.demo.service.SysUserNasService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nas-user")
@RequiredArgsConstructor
public class SysUserNasController {
    private final SysUserNasService sysUserNasService;
    /**
     * 查询
     */
    @GetMapping("/find")
    public RestResponse find(int id){
        return RestResponse.success(sysUserNasService.load(id));
    }

    /**
     * 查询
     */
    @PostMapping("/add")
    public RestResponse add(@RequestBody SysUserNasDTO sysUserNasDTO){
        return sysUserNasService.insert(sysUserNasDTO);
    }
    /**
     * 更新
     */
    @PatchMapping("/update")
    public RestResponse update(@RequestBody SysUserNasDTO sysUserNasDTO){
        return sysUserNasService.update(sysUserNasDTO);
    }
    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public RestResponse delete(@PathVariable Integer id){
        return sysUserNasService.delete(id);
    }
}
