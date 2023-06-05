package com.hyj.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyj.demo.bo.AddSysUserBO;
import com.hyj.demo.bo.QuerySysUserBO;
import com.hyj.demo.bo.UpdateSysUserBO;
import com.hyj.demo.common.RestResponse;
import com.hyj.demo.dto.SysUserDTO;
import com.hyj.demo.service.SysUserService;
import com.hyj.demo.vo.SysUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(tags = "用户管理")
public class SysUserController {
    private final SysUserService sysUserService;
    /**
     * 查询
     */
    @ApiOperation("根据id查询用户")
    @GetMapping("/find")
    public RestResponse<SysUserVO> find(int id){
        return RestResponse.success(sysUserService.load(id));
    }
    /**
     * 查询
     */
    @ApiOperation("分页查询用户")
    @PostMapping("/findByPage")
    public RestResponse<IPage<SysUserVO>> findByPage(@RequestBody QuerySysUserBO querySysUserBO){
        return RestResponse.success(sysUserService.getUserPage(querySysUserBO));
    }
    /**
     * 新增
     */
    @ApiOperation("新增用户")
    @PostMapping("/add")
    public RestResponse add(@RequestBody AddSysUserBO addSysUserBO){
        return sysUserService.insert(addSysUserBO);
    }
    /**
     * 更新
     */
    @ApiOperation("更新用户信息")
    @PatchMapping("/update")
    public RestResponse update(@RequestBody UpdateSysUserBO updateSysUserBO){
        return sysUserService.update(updateSysUserBO);
    }
    /**
     * 删除
     */
    @ApiOperation("根据id删除用户")
    @DeleteMapping("/delete/{id}")
    public RestResponse delete(@PathVariable Integer id){
        return sysUserService.delete(id);
    }
}
