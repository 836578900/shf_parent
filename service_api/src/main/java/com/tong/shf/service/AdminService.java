package com.tong.shf.service;

import com.tong.shf.entity.Admin;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-26 19:03
 */
public interface AdminService extends BaseService<Admin> {
    Admin getByUsername(String username);
}
