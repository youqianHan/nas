package com.hyj.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyj.demo.bo.AddSysUserBO;
import com.hyj.demo.bo.QuerySysUserBO;
import com.hyj.demo.bo.UpdateSysUserBO;
import com.hyj.demo.common.RestResponse;
import com.hyj.demo.common.enums.Status;
import com.hyj.demo.dto.SysUserDTO;
import com.hyj.demo.entity.SysUser;
import com.hyj.demo.mapper.SysUserMapper;
import com.hyj.demo.service.SysUserService;
import com.hyj.demo.vo.SysUserSelectVO;
import com.hyj.demo.vo.SysUserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {
    private final SysUserMapper sysUserMapper;

    @Override
    public RestResponse<String> insert(AddSysUserBO addSysUserBO) {
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(addSysUserBO,sysUser, CopyOptions.create().ignoreNullValue());
        this.save(sysUser);
        return RestResponse.success();
    }

    @Override
    public RestResponse<String> delete(List<Integer> ids) {
        int i = sysUserMapper.deleteBatchIds(ids);
        Assert.isTrue(i>0, Status.ENTITY_NOT_EXISTS.getMessage());
        return RestResponse.success();
    }

    @Override
    public RestResponse<String> update(UpdateSysUserBO updateSysUserBO) {
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(updateSysUserBO,sysUser, CopyOptions.create().ignoreNullValue());
        boolean b = updateById(sysUser);
        Assert.isTrue(b, Status.ENTITY_NOT_EXISTS.getMessage());
        return RestResponse.success();
    }

    @Override
    public SysUserVO load(int id) {
        SysUserVO sysUserVO = new SysUserVO();
        SysUser sysUser = sysUserMapper.loadSysUser(id);
        BeanUtil.copyProperties(sysUser,sysUserVO,CopyOptions.create().ignoreNullValue());
        return sysUserVO;
    }

    @Override
    public Map<String, Object> pageList(int offset, int pagesize) {
        return null;
    }


    @Override
    public IPage<SysUserVO> getUserPage(QuerySysUserBO querySysUserBO) {
        Page<SysUser> page = new Page<>(querySysUserBO.getPageNo(), querySysUserBO.getPageSize());
        SysUser sysUser = new SysUser();
        BeanUtil.copyProperties(querySysUserBO,sysUser,CopyOptions.create().ignoreNullValue());
        Page<SysUser> iPage = this.page(page, new LambdaQueryWrapper<SysUser>(sysUser));
        IPage<SysUserVO> sysUserVOIPage = new Page<>();
        BeanUtil.copyProperties(iPage,sysUserVOIPage);
        return sysUserVOIPage;
    }

    @Override
    public List<SysUserSelectVO> findUserList() {
        List<SysUser> userList = this.list();
        ArrayList<SysUserSelectVO> sysUserSelectVOS = new ArrayList<>();
        Map<String, List<SysUser>> collect = userList.stream().collect(Collectors.groupingBy(SysUser::getUserGroup));
        collect.forEach((k,v)->{
            SysUserSelectVO sysUserSelectVO = new SysUserSelectVO();
            sysUserSelectVO.setUserGroup(k);
            sysUserSelectVO.setSysUserVos(BeanUtil.copyToList(v,SysUserVO.class));
            sysUserSelectVOS.add(sysUserSelectVO);
        });
        return sysUserSelectVOS;
    }


}
