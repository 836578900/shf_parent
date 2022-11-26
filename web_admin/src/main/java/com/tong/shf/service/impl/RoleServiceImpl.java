package com.tong.shf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tong.shf.entity.Role;
import com.tong.shf.mapper.BaseMapper;
import com.tong.shf.mapper.RoleMapper;
import com.tong.shf.service.RoleService;
import com.tong.shf.util.CastUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-25 11:31
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;


    @Override
    public BaseMapper getEntityMapper() {
        return roleMapper;
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }
}
