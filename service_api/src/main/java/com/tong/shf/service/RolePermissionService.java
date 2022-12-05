package com.tong.shf.service;

import com.tong.shf.entity.RolePermission;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-05 17:43
 */
public interface RolePermissionService extends BaseService<RolePermission> {
    void  insertRolePermission(Long roleId,Long[] permissionIds);
}
