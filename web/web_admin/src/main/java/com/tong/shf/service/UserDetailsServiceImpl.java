package com.tong.shf.service;

import com.tong.shf.entity.Admin;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * title:
 * Description:
 *
 * @version 1.0
 * @Author Tong
 * @create 2022-12-05 20:31
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @DubboReference
    private  AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Admin admin = adminService.getByUsername(s);
        if (admin == null) {
            throw new UsernameNotFoundException("用户名没找到！！！");
        }

        return new User(s,admin.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(""));
    }
}
