package com.tong.shf.mapper;

import com.tong.shf.entity.AdminRole;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-04 18:41
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    List<Long> findRoleIdsByAdminId(long adminId);

    void  deleteByAdminId(long adminId);
}
