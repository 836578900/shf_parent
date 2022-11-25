package com.tong.shf.service.impl;

import com.tong.shf.entity.Role;
import com.tong.shf.mapper.RoleMapper;
import com.tong.shf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-25 11:31
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public void insert(Role role) {
        roleMapper.insert(role);
    }
}
