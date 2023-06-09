package com.hyj.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.hyj.demo.bo.AddNasInfoBO;
import com.hyj.demo.bo.UpdateNasInfoBO;
import com.hyj.demo.bo.common.QueryNasBO;
import com.hyj.demo.common.RestResponse;
import com.hyj.demo.common.enums.Status;
import com.hyj.demo.dto.NasInfoDTO;
import com.hyj.demo.dto.SysUserNasDTO;
import com.hyj.demo.entity.NasInfo;
import com.hyj.demo.entity.SysUser;
import com.hyj.demo.entity.SysUserNas;
import com.hyj.demo.mapper.NasInfoMapper;
import com.hyj.demo.service.NasInfoService;
import com.hyj.demo.service.SysUserNasService;
import com.hyj.demo.util.NasFileUtils;
import com.hyj.demo.vo.NasInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @description nas_info
 * @author rich
 * @date 2023-05-30 17:26:20
 */
@Service
@RequiredArgsConstructor
public class NasInfoServiceImpl extends ServiceImpl<NasInfoMapper,NasInfo> implements NasInfoService {

    private final NasInfoMapper nasInfoMapper;
    private final SysUserNasService sysUserNasService;

    @Override
    public RestResponse<String> insert(AddNasInfoBO addNasInfoBO) {
        NasInfo nasInfo = new NasInfo();
        BeanUtil.copyProperties(addNasInfoBO,nasInfo, CopyOptions.create().ignoreNullValue());
        this.save(nasInfo);
        if(Objects.nonNull(addNasInfoBO.getLinkUserId())){
            SysUserNasDTO sysUserNasDTO = new SysUserNasDTO();
            //填充nasId
            sysUserNasDTO.setNasId(nasInfo.getId());
            //填充userId
            //建立nas-user关联关系
            linkUser(addNasInfoBO.getLinkUserId(),nasInfo.getId());
        }
        return RestResponse.success();
    }

    @Override
    public RestResponse<String> delete(List<Integer> ids) {
        int i = nasInfoMapper.deleteBatchIds(ids);
        Assert.isTrue(i>0, Status.ENTITY_NOT_EXISTS.getMessage());
        return RestResponse.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResponse<String> update(UpdateNasInfoBO updateNasInfoBO) {
        NasInfo nasInfo = new NasInfo();
        BeanUtil.copyProperties(updateNasInfoBO,nasInfo, CopyOptions.create().ignoreNullValue());
        boolean b = updateById(nasInfo);
        if(Objects.nonNull(updateNasInfoBO.getLinkUserId())){
            //先根据nasId查询是否已建立关联关系  如果查询为null则新建关联关系
            SysUserNas sysUserNas = sysUserNasService.loadByNasId(updateNasInfoBO.getId());
            if(Objects.isNull(sysUserNas)){
                //创建关联关系
                linkUser(updateNasInfoBO.getLinkUserId(),updateNasInfoBO.getId());
            }else{
                //更新关联用户
                sysUserNasService.updateLinkUserByNasId(updateNasInfoBO.getLinkUserId(),updateNasInfoBO.getId());
            }
        }
        Assert.isTrue(b, Status.ENTITY_NOT_EXISTS.getMessage());
        return RestResponse.success();
    }

    @Override
    public NasInfoVO load(int id) {
        NasInfo nasInfo = nasInfoMapper.loadNasInfo(id);
        NasInfoVO nasInfoVO = new NasInfoVO();
        BeanUtil.copyProperties(nasInfo,nasInfoVO,CopyOptions.create().ignoreNullValue());
        SysUserNas sysUserNas = sysUserNasService.loadByNasId(nasInfo.getId());
        if(Objects.nonNull(sysUserNas)){
            nasInfoVO.setLinkUserId(sysUserNas.getUserId());
        }
        return nasInfoVO;
    }

    @Override
    public IPage<NasInfoVO> getNasPage(QueryNasBO queryNasBO) {
        Page<NasInfo> page = new Page<>(queryNasBO.getPageNo(), queryNasBO.getPageSize());
        NasInfo nasInfo = new NasInfo();
        BeanUtil.copyProperties(queryNasBO, nasInfo, CopyOptions.create().ignoreNullValue());
        Page<NasInfo> iPage = this.page(page, new MPJLambdaWrapper<NasInfo>()
                                              .leftJoin(SysUserNas.class,SysUserNas::getNasId,NasInfo::getId)
                                                .leftJoin(SysUser.class,SysUser::getId,SysUserNas::getUserId)
                                              .setEntity(nasInfo).selectAll(NasInfo.class)
                                              .eq(Objects.nonNull(queryNasBO.getLinkUserName()),SysUser::getUserName,queryNasBO.getLinkUserName())
                                              .eq(Objects.nonNull(queryNasBO.getLinkUserSoeid()),SysUser::getSoeid,queryNasBO.getLinkUserSoeid())
                                              .selectAs(SysUser::getId,"linkUserId")
                                              .selectAs(SysUser::getUserName,"linkUserName")
                                              .selectAs(SysUser::getSoeid,"linkUserSoeid"));

        IPage<NasInfoVO> sysUserVOIPage = new Page<>();
        BeanUtil.copyProperties(iPage, sysUserVOIPage);
        sysUserVOIPage.setRecords(BeanUtil.copyToList(iPage.getRecords(),NasInfoVO.class));
//        for (NasInfoVO record : sysUserVOIPage.getRecords()) {
//            record.setNarsSizeStr(NasFileUtils.convertMemory(record.getNarsSize().longValue()));
//            record.setNarsAvailStr(NasFileUtils.convertMemory(record.getNarsAvail().longValue()));
//            record.setNarsUsedStr(NasFileUtils.convertMemory(record.getNarsUsed().longValue()));
//        }
        return sysUserVOIPage;
    }

    /**
     * 查询nas盘以及关联的用户信息
     * @param queryNasBO
     * @return
     */
    public List<NasInfoDTO> getNasList(QueryNasBO queryNasBO) {
        NasInfo nasInfo = new NasInfo();
        BeanUtil.copyProperties(queryNasBO, nasInfo, CopyOptions.create().ignoreNullValue());
        List<NasInfo> nasInfoList = this.list(new MPJLambdaWrapper<NasInfo>()
                .leftJoin(SysUserNas.class, SysUserNas::getNasId, NasInfo::getId)
                .leftJoin(SysUser.class, SysUser::getId, SysUserNas::getUserId)
                .setEntity(nasInfo).selectAll(NasInfo.class)
                .eq(Objects.nonNull(queryNasBO.getLinkUserName()), SysUser::getUserName, queryNasBO.getLinkUserName())
                .eq(Objects.nonNull(queryNasBO.getLinkUserSoeid()), SysUser::getSoeid, queryNasBO.getLinkUserSoeid())
                .selectAs(SysUser::getId, "linkUserId")
                .selectAs(SysUser::getUserName, "linkUserName")
                .selectAs(SysUser::getEmail, "linkUserEmail")
                .selectAs(SysUser::getSoeid, "linkUserSoeid"));
        List<NasInfoDTO> nasInfoDTOS = BeanUtil.copyToList(nasInfoList, NasInfoDTO.class);
        return nasInfoDTOS;
    }




    private void linkUser(Long userId,Long nasId){
        //填充userId
        SysUserNasDTO sysUserNasDTO = new SysUserNasDTO();
        sysUserNasDTO.setUserId(userId);
        sysUserNasDTO.setNasId(nasId);
        //建立nas-user关联关系
        sysUserNasService.insert(sysUserNasDTO);
    }

}
