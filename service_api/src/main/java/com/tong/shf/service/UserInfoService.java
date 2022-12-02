package com.tong.shf.service;

import com.tong.shf.entity.UserInfo;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-02 20:57
 */
public interface UserInfoService  extends BaseService<UserInfo> {

    UserInfo findUserInfoByPhone(String phone);
}
