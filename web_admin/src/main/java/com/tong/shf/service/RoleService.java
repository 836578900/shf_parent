package com.tong.shf.service;

import com.tong.shf.entity.Role;

import java.util.List;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-25 11:31
 */
public interface RoleService {
    List<Role> findAll();
    void insert(Role role);
}
