package com.hyj.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hyj.demo.bo.AddNasInfoBO;
import com.hyj.demo.bo.QuerySysUserBO;
import com.hyj.demo.bo.UpdateNasInfoBO;
import com.hyj.demo.bo.common.QueryNasBO;
import com.hyj.demo.common.RestResponse;
import com.hyj.demo.dto.NasInfoDTO;
import com.hyj.demo.dto.NasInfoDTO;
import com.hyj.demo.entity.SendMailFlow;
import com.hyj.demo.service.NasInfoService;
import com.hyj.demo.service.SendMailFlowService;
import com.hyj.demo.service.SysUserService;
import com.hyj.demo.vo.NasInfoVO;
import com.hyj.demo.vo.SysUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nas")
@RequiredArgsConstructor
@Api(tags = "nas管理")
public class NasController {
    private final NasInfoService nasInfoService;
    private final SendMailFlowService sendMailFlowService;
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
    @PostMapping("/update")
    public RestResponse update(@RequestBody UpdateNasInfoBO updateNasInfoBO){
        return nasInfoService.update(updateNasInfoBO);
    }
    /**
     * 删除
     */
    @PostMapping("/delete")
    public RestResponse delete(@RequestBody List<Integer> ids){
        return nasInfoService.delete(ids);
    }

    /**
     * 查询
     */
    @ApiOperation("分页查询用户")
    @PostMapping("/findByPage")
    public RestResponse<IPage<NasInfoVO>> findByPage(@RequestBody QueryNasBO queryNasBO){
        return RestResponse.success(nasInfoService.getNasPage(queryNasBO));
    }


    /**
     * 查询
     */
    @ApiOperation("分页查询用户")
    @GetMapping("/sendemailflow")
    public RestResponse<List<SendMailFlow>> findByPage(@RequestParam String soeId){
        return RestResponse.success(sendMailFlowService.loadBySoeid(soeId));
    }
}
