package com.tong.shf.mapper;

import com.tong.shf.entity.Permission;

import java.io.Serializable;
import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-05 17:19
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> findAll();
    List<Permission> findByAdminId(Long adminId);
    List<Permission> findPermissionByParentId(Serializable id);
}
