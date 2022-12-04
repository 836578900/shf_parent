package com.tong.shf.service.impl;

import com.tong.shf.entity.AdminRole;
import com.tong.shf.mapper.AdminRoleMapper;
import com.tong.shf.mapper.BaseMapper;
import com.tong.shf.service.AdminRoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-04 18:58
 */
@DubboService
public class AdminRoleServiceImpl extends BaseServiceImpl<AdminRole> implements AdminRoleService {
   @Autowired
   private AdminRoleMapper adminRoleMapper;

    @Override
    public BaseMapper<AdminRole> getEntityMapper() {
        return adminRoleMapper;
    }

    @Override
    @Transactional
    public void insertAdminRole(Long adminId, Long[] roleIds) {
        //先删除所有角色为adminId的
        adminRoleMapper.deleteByAdminId(adminId);
        //再循环添加记录
        for (Long roleId : roleIds) {
            if (roleId == null) {
                continue;
            }
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId);
            adminRoleMapper.insert(adminRole);
        }
    }
}
