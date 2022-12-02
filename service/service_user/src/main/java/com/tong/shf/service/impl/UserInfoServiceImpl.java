package com.tong.shf.service.impl;

import com.tong.shf.entity.UserInfo;
import com.tong.shf.mapper.BaseMapper;
import com.tong.shf.mapper.UserInfoMapper;
import com.tong.shf.service.UserInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-02 20:57
 */
@DubboService
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public BaseMapper<UserInfo> getEntityMapper() {
        return userInfoMapper;
    }

    @Override
    public UserInfo findUserInfoByPhone(String phone) {
        return userInfoMapper.findUserInfoByPhone(phone);
    }
}
