package com.tong.shf.service.impl;

import com.tong.shf.entity.RolePermission;
import com.tong.shf.mapper.BaseMapper;
import com.tong.shf.mapper.RolePermissionMapper;
import com.tong.shf.service.RolePermissionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-05 17:43
 */
@DubboService
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission> implements RolePermissionService {
    @Autowired
    private RolePermissionMapper rolePermissionMapper;


    @Override
    public BaseMapper<RolePermission> getEntityMapper() {
        return rolePermissionMapper;
    }

    @Override
    @Transactional
    public void insertRolePermission(Long roleId, Long[] permissionIds) {
        rolePermissionMapper.deleteByRoleId(roleId);
        for (Long permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionMapper.insert(rolePermission);
        }
    }
}
