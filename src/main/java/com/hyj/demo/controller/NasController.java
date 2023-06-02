package com.hyj.demo.controller;

import com.hyj.demo.bo.AddNasInfoBO;
import com.hyj.demo.bo.UpdateNasInfoBO;
import com.hyj.demo.common.RestResponse;
import com.hyj.demo.dto.NasInfoDTO;
import com.hyj.demo.dto.NasInfoDTO;
import com.hyj.demo.service.NasInfoService;
import com.hyj.demo.service.SysUserService;
import com.hyj.demo.vo.NasInfoVO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nas")
@RequiredArgsConstructor
@Api(tags = "nas管理")
public class NasController {
    private final NasInfoService nasInfoService;
    /**
     * 查询
     */
    @GetMapping("/find")
    public RestResponse<NasInfoVO> find(int id){
        return RestResponse.success(nasInfoService.load(id));
    }

    /**
     * 查询
     */
    @PostMapping("/add")
    public RestResponse add(@RequestBody AddNasInfoBO addNasInfoBO){
        return nasInfoService.insert(addNasInfoBO);
    }
    /**
     * 更新
     */
    @PatchMapping("/update")
    public RestResponse update(@RequestBody UpdateNasInfoBO updateNasInfoBO){
        return nasInfoService.update(updateNasInfoBO);
    }
    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public RestResponse delete(@PathVariable Integer id){
        return nasInfoService.delete(id);
    }
}
