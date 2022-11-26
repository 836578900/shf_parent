package com.tong.shf.service.impl;

import com.tong.shf.entity.Admin;
import com.tong.shf.mapper.AdminMapper;
import com.tong.shf.mapper.BaseMapper;
import com.tong.shf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-11-26 19:03
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public BaseMapper getEntityMapper() {
        return adminMapper;
    }

}
