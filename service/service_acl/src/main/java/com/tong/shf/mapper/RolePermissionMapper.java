package com.tong.shf.mapper;

import com.tong.shf.entity.RolePermission;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-05 17:20
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    List<Long> findPermissionIdsByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);
}
