package com.tong.shf.service;

import com.tong.shf.entity.AdminRole;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-04 18:18
 */
public interface AdminRoleService extends BaseService<AdminRole> {
    void insertAdminRole(Long adminId,Long[] roleIds);
}
