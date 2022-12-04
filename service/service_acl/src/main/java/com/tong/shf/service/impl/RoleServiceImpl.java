package com.tong.shf.service.impl;

import com.tong.shf.entity.Role;
import com.tong.shf.mapper.AdminRoleMapper;
import com.tong.shf.mapper.BaseMapper;
import com.tong.shf.mapper.RoleMapper;
import com.tong.shf.service.RoleService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
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
@DubboService
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;


    @Override
    public BaseMapper getEntityMapper() {
        return roleMapper;
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public Map<String, List<Role>> findRoleByAdminId(Long adminId) {
        List<Role> roleList = roleMapper.findAll();
        List<Long> roleIds = adminRoleMapper.findRoleIdsByAdminId(adminId);
        ArrayList<Role> noAssignRoleList = new ArrayList<>();
        ArrayList<Role> assignRoleList = new ArrayList<>();
        for (Role role : roleList) {
            if (roleIds.contains(role.getId())){
                assignRoleList.add(role);
            }else {
                noAssignRoleList.add(role);
            }
        }
        HashMap<String, List<Role>> map = new HashMap<>();
        map.put("noAssignRoleList",noAssignRoleList);
        map.put("assignRoleList",assignRoleList);
        return map;
    }
}
