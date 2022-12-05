package com.tong.shf.service;

import com.tong.shf.entity.Permission;

import java.util.List;
import java.util.Map;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-05 17:17
 */
public interface PermissionService extends BaseService<Permission>{
    List<Map<String,Object>> findZNodes(Long roleId);

    List<Permission> findByAdminId(Long adminId);

    List<Permission> findAll();
}
