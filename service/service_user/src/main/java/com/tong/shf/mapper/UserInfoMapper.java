package com.tong.shf.mapper;

import com.tong.shf.entity.UserInfo;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-02 20:59
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    UserInfo findUserInfoByPhone(String phone);
}
